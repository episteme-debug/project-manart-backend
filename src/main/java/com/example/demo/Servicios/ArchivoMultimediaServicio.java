package com.example.demo.Servicios;

import com.example.demo.DTOs.ArchivoMultimediaDTO;
import com.example.demo.Entidades.ArchivoMultimedia;
import com.example.demo.Enums.TipoArchivoEnum;
import com.example.demo.Interfaces.ArchivoMultimediaInterfaz;
import com.example.demo.Repositorios.ArchivoMultimediaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.Enums.TipoArchivoEnum.*;

@Service
public class ArchivoMultimediaServicio {

    @Autowired
    ArchivoMultimediaRepositorio archivoMultimediaRepositorio;

    private static final String dirPersonal = "D:\\Manart\\backend\\";
    private static final String dirGeneral = "project-manart-backend\\src\\main\\resources\\static\\cargascliente\\";
    private static final String dirCategoriaProductos = dirPersonal + dirGeneral + "categoriaproductos\\";
    private static final String dirProductos = dirPersonal + dirGeneral + "productos\\";
    private static final String dirPublicaciones = dirPersonal + dirGeneral + "publicaciones\\";
    private static final String dirUsuarios = dirPersonal + dirGeneral + "usuarios\\";

    // Metodo reutilizable que encuentra el tipo de archivo extrayendo una parte de getContentType
    public TipoArchivoEnum obtenerTipoArchivo(String tipoFormato) throws IOException {
        if (tipoFormato.startsWith("image/")) return TipoArchivoEnum.IMAGEN;
        if (tipoFormato.startsWith("video/")) return TipoArchivoEnum.VIDEO;
        if (tipoFormato.startsWith("audio/")) return TipoArchivoEnum.AUDIO;
        throw new IOException("Tipo de archivo no permitido: " + tipoFormato);
    }

    // Metodo reutilizable que identifica la entidad que se quiere relacionar
    public String obtenerDirectorioBase(ArchivoMultimediaDTO relacion) throws IOException {
        if (relacion.getUsuario() != null) return dirUsuarios;
        if (relacion.getProducto() != null) return dirProductos;
        if (relacion.getPublicacion() != null) return dirPublicaciones;
        if (relacion.getCategoriaProducto() != null) return dirCategoriaProductos;
        throw new IOException("Relación no válida para el archivo");
    }

    // Metodo reutilizable que identifica la subcarpeta a partir del metodo obtenerTipoArchivo
    public String obtenerSubcarpeta(TipoArchivoEnum tipoArchivo) {
        switch (tipoArchivo) {
            case IMAGEN: return "imagen";
            case VIDEO: return "video";
            case AUDIO: return "audio";
            default: return "";
        }
    }

    // Metodo reutilizable por transferir archivos
    public ArchivoMultimedia transferirArchivo(MultipartFile archivo, ArchivoMultimediaDTO relacion) throws IOException {
        if (archivo.isEmpty()) throw new IOException("El archivo está vacío");
        // HACE REFERENCIA A LA TRANFERENCIA FÍSICA DEL ARCHIVO
        String tipoFormato = archivo.getContentType(); //audio.mp3
        String nombreArchivo = archivo.getOriginalFilename(); //logo_spring.jpg

        TipoArchivoEnum tipoArchivo = obtenerTipoArchivo(tipoFormato);
        String dirBase = obtenerDirectorioBase(relacion);
        String subcarpeta = obtenerSubcarpeta(tipoArchivo);

        String rutaArchivo = dirBase + "\\" + subcarpeta + File.separator + nombreArchivo;
        archivo.transferTo(Paths.get(rutaArchivo));


        // HACE REFERENCIA AL ALMACENAMIENTO DEL ARCHIVO EN LA BASE DE DATOS
        ArchivoMultimedia archivox = new ArchivoMultimedia();
        archivox.setNombre(nombreArchivo);
        archivox.setRuta(rutaArchivo);
        archivox.setTipo(tipoArchivo);

        // Asignar la relación correspondiente
        archivox.setUsuario(relacion.getUsuario());
        archivox.setProducto(relacion.getProducto());
        archivox.setPublicacion(relacion.getPublicacion());
        archivox.setCategoriaProducto(relacion.getCategoriaProducto());

        return archivoMultimediaRepositorio.save(archivox);
    }

    // Metodo final para transferir archivos sea uno o varios
    public List<ArchivoMultimedia> transferirArchivos(List<MultipartFile> archivos, ArchivoMultimediaDTO relacion) throws IOException {
        List<ArchivoMultimedia> guardados = new ArrayList<>();

        for (MultipartFile archivo : archivos) {
            ArchivoMultimedia guardado = transferirArchivo(archivo, relacion);
            guardados.add(guardado);
        }

        return guardados;
    }


    public ArchivoMultimedia obtenerPorId(Long id){
        return archivoMultimediaRepositorio.findById(id).get();
    }

    public List<ArchivoMultimediaInterfaz> listarArchivosPorPublicacion(Long id){
        return archivoMultimediaRepositorio.findByPublicacion_Id(id);
    }

    public List<ArchivoMultimediaInterfaz> listarArchivosPorUsuario(Long id){
        return archivoMultimediaRepositorio.findByUsuario_IdUsuario(id);
    }

    public List<ArchivoMultimediaInterfaz> listarArchivosPorCategoria(Long id){
        return archivoMultimediaRepositorio.findByCategoriaProducto_IdCategoria(id);
    }

    public List<ArchivoMultimediaInterfaz> listarArchivosPorProducto(Long id){
        return archivoMultimediaRepositorio.findByProducto_IdProducto(id);
    }

    public void eliminarPorId(Long id) throws IOException {
        ArchivoMultimedia archivo = archivoMultimediaRepositorio.findById(id).get();
        // Eliminar archivo físicamente
        String ruta = archivo.getRuta();
        Path path = Paths.get(ruta);
        Files.deleteIfExists(path);

        // Elimina el archivo de la base de datos
        archivoMultimediaRepositorio.deleteById(id);;
    }

}