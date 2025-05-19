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
import java.util.NoSuchElementException;

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
        if (tipoFormato == null || tipoFormato.isEmpty()) {
            throw new IOException("Formato de archivo no especificado.");
        }
        if (tipoFormato.startsWith("image/")) return TipoArchivoEnum.IMAGEN;
        if (tipoFormato.startsWith("video/")) return TipoArchivoEnum.VIDEO;
        if (tipoFormato.startsWith("audio/")) return TipoArchivoEnum.AUDIO;
        throw new IOException("Tipo de archivo no permitido: " + tipoFormato);
    }

    // Metodo reutilizable que identifica la entidad que se quiere relacionar
    public String obtenerDirectorioBase(ArchivoMultimediaDTO relacion) throws IOException {
        if (relacion == null) {
            throw new IOException("Relación no puede ser nula.");
        }
        if (relacion.getUsuario() != null) return dirUsuarios;
        if (relacion.getProducto() != null) return dirProductos;
        if (relacion.getPublicacion() != null) return dirPublicaciones;
        if (relacion.getCategoriaProducto() != null) return dirCategoriaProductos;
        throw new IOException("Relación no válida para el archivo.");
    }

    // Metodo reutilizable que identifica la subcarpeta a partir del metodo obtenerTipoArchivo
    public String obtenerSubcarpeta(TipoArchivoEnum tipoArchivo) {
        if (tipoArchivo == null) {
            throw new IllegalArgumentException("Tipo de archivo no puede ser nulo.");
        }
        switch (tipoArchivo) {
            case IMAGEN: return "imagen";
            case VIDEO: return "video";
            case AUDIO: return "audio";
            default: throw new IllegalArgumentException("Subcarpeta no definida para el tipo de archivo.");
        }
    }

    // Metodo reutilizable por transferir archivos
    public ArchivoMultimedia transferirArchivo(MultipartFile archivo, ArchivoMultimediaDTO relacion) throws IOException {
        if (archivo == null) {
            throw new IOException("Archivo no proporcionado.");
        }
        if (archivo.isEmpty()) {
            throw new IOException("El archivo está vacío.");
        }

        String tipoFormato = archivo.getContentType();
        String nombreArchivo = archivo.getOriginalFilename();

        if (nombreArchivo == null || nombreArchivo.isEmpty()) {
            throw new IOException("El nombre del archivo no puede ser nulo o vacío.");
        }

        TipoArchivoEnum tipoArchivo = obtenerTipoArchivo(tipoFormato);
        String dirBase = obtenerDirectorioBase(relacion);
        String subcarpeta = obtenerSubcarpeta(tipoArchivo);

        String rutaArchivo = dirBase + "\\" + subcarpeta + File.separator + nombreArchivo;
        archivo.transferTo(Paths.get(rutaArchivo));

        ArchivoMultimedia archivox = new ArchivoMultimedia();
        archivox.setNombre(nombreArchivo);
        archivox.setRuta(rutaArchivo);
        archivox.setTipo(tipoArchivo);

        archivox.setUsuario(relacion.getUsuario());
        archivox.setProducto(relacion.getProducto());
        archivox.setPublicacion(relacion.getPublicacion());
        archivox.setCategoriaProducto(relacion.getCategoriaProducto());

        return archivoMultimediaRepositorio.save(archivox);
    }

    public List<ArchivoMultimedia> transferirArchivos(List<MultipartFile> archivos, ArchivoMultimediaDTO relacion) throws IOException {
        if (archivos == null || archivos.isEmpty()) {
            throw new IOException("No se proporcionaron archivos para transferir.");
        }

        List<ArchivoMultimedia> guardados = new ArrayList<>();
        for (MultipartFile archivo : archivos) {
            guardados.add(transferirArchivo(archivo, relacion));
        }

        return guardados;
    }

    public ArchivoMultimedia obtenerPorId(Long id) {
        ArchivoMultimedia archivo = archivoMultimediaRepositorio.findById(id).get();
        if (archivo == null){
            throw new NoSuchElementException("Archivo multimedia no encontrado con ID: " + id);
        }
        return archivo;
    }

    public List<ArchivoMultimediaInterfaz> listarArchivosPorPublicacion(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID de publicación no puede ser nulo.");
        }
        return archivoMultimediaRepositorio.findByPublicacion_Id(id);
    }

    public List<ArchivoMultimediaInterfaz> listarArchivosPorUsuario(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID de usuario no puede ser nulo.");
        }
        return archivoMultimediaRepositorio.findByUsuario_IdUsuario(id);
    }

    public List<ArchivoMultimediaInterfaz> listarArchivosPorCategoria(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID de categoría no puede ser nulo.");
        }
        return archivoMultimediaRepositorio.findByCategoriaProducto_IdCategoria(id);
    }

    public List<ArchivoMultimediaInterfaz> listarArchivosPorProducto(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID de producto no puede ser nulo.");
        }
        return archivoMultimediaRepositorio.findByProducto_IdProducto(id);
    }

    public void eliminarPorId(Long id) throws IOException {
        ArchivoMultimedia archivo = archivoMultimediaRepositorio.findById(id).get();
        if (archivo == null){
            throw new NoSuchElementException("Archivo multimedia no encontrado con ID: " + id);
        }

        String ruta = archivo.getRuta();
        if (ruta == null || ruta.isEmpty()) {
            throw new IOException("Ruta del archivo no especificada.");
        }

        Path path = Paths.get(ruta);
        Files.deleteIfExists(path);
        archivoMultimediaRepositorio.deleteById(id);
    }
}
