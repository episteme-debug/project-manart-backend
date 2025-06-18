package com.example.demo.Controladores;

import com.example.demo.Entidades.ArchivoMultimedia;
import com.example.demo.Enums.EntidadesArchivoMultimediaEnum;
import com.example.demo.Enums.TipoArchivoEnum;
import com.example.demo.Servicios.ArchivoMultimediaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin("http://127.0.0.1:5500/")
@RequestMapping("/api/archivomultimedia/")
@RestController
public class ArchivoMultimediaControlador {
///api/archivomultimedia/private/transferirarchivos
    @Autowired
    private ArchivoMultimediaServicio archivoMultimediaServicio;

    /* Transfiere una lista de archivos a un directorio correspondiente según la entidad y el ID del objeto. */
    @PostMapping("private/transferirarchivos/{entidad}/{idObjeto}")
    public ResponseEntity<?> transferirArchivos(
            @RequestPart("archivos") List<MultipartFile> archivos,
            @PathVariable EntidadesArchivoMultimediaEnum entidad,
            @PathVariable Long idObjeto) {
        try {
            List<ArchivoMultimedia> archivosTransferidos = archivoMultimediaServicio.transferirArchivos(archivos, entidad, idObjeto);
            return ResponseEntity.ok(archivosTransferidos);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al transferir archivos: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error de aplicación: " + e.getMessage());
        }
    }

    // Lista todos los archivos multimedia relacionados a una entidad específica y un ID.
    @GetMapping("private/listararchivos/{entidad}/{idObjeto}")
    public ResponseEntity<?> listarArchivosPorEntidadYId(
            @PathVariable EntidadesArchivoMultimediaEnum entidad,
            @PathVariable Long idObjeto) {
        try {
            List<ArchivoMultimedia> listaArchivos = archivoMultimediaServicio.listarArchivosPorEntidadYId(entidad, idObjeto);
            return ResponseEntity.ok(listaArchivos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al listar archivos: " + e.getMessage());
        }
    }

    // Lista los archivos de una entidad, ID y tipo específico.
    @GetMapping("private/listararchivos/{entidad}/{idObjeto}/{tipo}")
    public ResponseEntity<?> listarArchivosPorEntidadYIdYTipo(
            @PathVariable EntidadesArchivoMultimediaEnum entidad,
            @PathVariable Long idObjeto,
            @PathVariable TipoArchivoEnum tipo) {
        try {
            List<ArchivoMultimedia> listaArchivos = archivoMultimediaServicio.listarArchivosPorEntidadYIdYTipo(entidad, idObjeto, tipo);
            return ResponseEntity.ok(listaArchivos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al filtrar archivos: " + e.getMessage());
        }
    }

    // Obtiene un archivo por su ID.
    @GetMapping("public/obtenerporid/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        try {
            ArchivoMultimedia archivo = archivoMultimediaServicio.obtenerPorId(id);
            return ResponseEntity.ok(archivo);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Archivo no encontrado con ID: " + id);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflicto: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: " + e.getMessage());
        }
    }

    // Elimina un archivo por su ID.
    @DeleteMapping("private/eliminarporid/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable Long id) {
        try {
            archivoMultimediaServicio.eliminarPorId(id);
            return ResponseEntity.ok("Archivo eliminado correctamente con ID: " + id);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el archivo con ID: " + id);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflicto: " + e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el archivo físico: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: " + e.getMessage());
        }
    }
}
