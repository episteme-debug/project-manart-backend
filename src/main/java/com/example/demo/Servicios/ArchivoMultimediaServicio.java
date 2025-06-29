package com.example.demo.Servicios;

import com.example.demo.Entidades.ArchivoMultimedia;
import com.example.demo.Enums.EntidadesArchivoMultimediaEnum;
import com.example.demo.Enums.TipoArchivoEnum;
import com.example.demo.Repositorios.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.NoSuchElementException;

import static com.example.demo.Enums.TipoArchivoEnum.*;

@Service
@RequiredArgsConstructor
public class ArchivoMultimediaServicio {

    private final ArchivoMultimediaRepositorio archivoMultimediaRepositorio;
    private final ProductoRepositorio productoRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final CategoriaProductoRepositorio categoriaProductoRepositorio;
    private final PublicacionRepositorio publicacionRepositorio;
    
    @Value("${app.upload.static.dir}")
    private String DIR_BASE;

    private static final Map<EntidadesArchivoMultimediaEnum, String> DIRECTORIOS = Map.of(
            EntidadesArchivoMultimediaEnum.Usuario, "usuarios",
            EntidadesArchivoMultimediaEnum.Producto, "productos",
            EntidadesArchivoMultimediaEnum.Publicacion, "publicaciones",
            EntidadesArchivoMultimediaEnum.CategoriaProducto, "categoriaproductos"
    );


    public TipoArchivoEnum obtenerTipoArchivo(String tipoFormato) throws IOException {
        if (tipoFormato == null || tipoFormato.isEmpty())
            throw new IOException("Formato de archivo no especificado.");

        return switch (tipoFormato.split("/")[0]) {
            case "image" -> IMAGEN;
            case "video" -> VIDEO;
            case "audio" -> AUDIO;
            default -> throw new IOException("Tipo de archivo no permitido: " + tipoFormato);
        };
    }

    public String obtenerDirectorioBase(EntidadesArchivoMultimediaEnum entidad) throws IOException {
        String dir = DIRECTORIOS.get(entidad);
        if (dir == null)
            throw new IOException("Relación no válida o nula para el archivo.");
        return dir;
    }

    public String obtenerSubcarpeta(TipoArchivoEnum tipo) {
        return switch (tipo) {
            case IMAGEN -> "imagen";
            case VIDEO -> "video";
            case AUDIO -> "audio";
        };
    }

    public ArchivoMultimedia transferirArchivo(MultipartFile archivo, EntidadesArchivoMultimediaEnum entidad, Long idObjeto) throws IOException {
        validarArchivo(archivo);

        String nombreArchivo = archivo.getOriginalFilename();
        TipoArchivoEnum tipo = obtenerTipoArchivo(archivo.getContentType());
        String subCarpeta = obtenerSubcarpeta(tipo);
        String directorioBase =  obtenerDirectorioBase(entidad);
        String rutaCompleta = DIR_BASE + "cargascliente/" + directorioBase + "/" + subCarpeta + "/" + nombreArchivo;
        String rutaRelativa = "cargascliente/" + directorioBase + "/" + subCarpeta + "/" + nombreArchivo;

        archivo.transferTo(Paths.get(rutaCompleta));

        if (!comprobarExistenciaObjeto(entidad, idObjeto)) {
            throw new NoSuchElementException(entidad + " con id " + idObjeto + " no existe");
        }

        ArchivoMultimedia nuevo = new ArchivoMultimedia();
        nuevo.setNombre(nombreArchivo);
        nuevo.setRuta(rutaRelativa);
        nuevo.setTipo(tipo);
        nuevo.setTipoEntidad(entidad);
        nuevo.setIdObjetoEntidad(idObjeto);

        return archivoMultimediaRepositorio.save(nuevo);
    }

    public List<ArchivoMultimedia> transferirArchivos(List<MultipartFile> archivos, EntidadesArchivoMultimediaEnum entidad, Long idObjeto) throws IOException {
        if (archivos == null || archivos.isEmpty())
            throw new IOException("No se proporcionaron archivos.");
        List<ArchivoMultimedia> resultado = new ArrayList<>();
        for (MultipartFile archivo : archivos) {
            resultado.add(transferirArchivo(archivo, entidad, idObjeto));
        }
        return resultado;
    }

    public List<ArchivoMultimedia> listarArchivosPorEntidadYId(EntidadesArchivoMultimediaEnum entidad, Long idObjeto) {
        validarExistencia(entidad, idObjeto);
        return archivoMultimediaRepositorio.findByTipoEntidadAndIdObjetoEntidad(entidad, idObjeto);
    }

    public List<ArchivoMultimedia> listarArchivosPorEntidadYIdYTipo(EntidadesArchivoMultimediaEnum entidad, Long idObjeto, TipoArchivoEnum tipo) {
        validarExistencia(entidad, idObjeto);
        return archivoMultimediaRepositorio.findByTipoEntidadAndIdObjetoEntidadAndTipo(entidad, idObjeto, tipo);
    }

    public ArchivoMultimedia obtenerPorId(Long id) {
        return archivoMultimediaRepositorio.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Elemento no encontrado"));
    }

    public void eliminarPorId(Long id) throws IOException {
        ArchivoMultimedia archivo = obtenerPorId(id);
        String ruta = archivo.getRuta();
        String rutaCompleta = DIR_BASE + ruta;
        if (ruta == null || ruta.isEmpty())
            throw new IOException("Ruta del archivo no especificada.");

        Files.deleteIfExists(Paths.get(rutaCompleta));
        archivoMultimediaRepositorio.deleteById(id);
    }

    // Métodos auxiliares

    private void validarArchivo(MultipartFile archivo) throws IOException {
        if (archivo == null || archivo.isEmpty())
            throw new IOException("Archivo no proporcionado o vacío.");
        if (archivo.getOriginalFilename() == null || archivo.getOriginalFilename().isEmpty())
            throw new IOException("Nombre de archivo no puede ser nulo o vacío.");
    }

    private void validarExistencia(EntidadesArchivoMultimediaEnum entidad, Long id) {
        if (!comprobarExistenciaObjeto(entidad, id)) {
            throw new NoSuchElementException(entidad + " con id " + id + " no existe");
        }
    }

    public boolean comprobarExistenciaObjeto(EntidadesArchivoMultimediaEnum entidad, Long id) {
        return switch (entidad) {
            case Usuario -> usuarioRepositorio.existsById(id);
            case Producto -> productoRepositorio.existsById(id);
            case Publicacion -> publicacionRepositorio.existsById(id);
            case CategoriaProducto -> categoriaProductoRepositorio.existsById(id);
        };
    }
}
