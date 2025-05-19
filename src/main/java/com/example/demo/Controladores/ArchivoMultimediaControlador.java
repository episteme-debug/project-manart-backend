package com.example.demo.Controladores;

import com.example.demo.DTOs.ArchivoMultimediaDTO;
import com.example.demo.Entidades.ArchivoMultimedia;
import com.example.demo.Interfaces.ArchivoMultimediaInterfaz;
import com.example.demo.Servicios.ArchivoMultimediaServicio;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    @Autowired
    ArchivoMultimediaServicio archivoMultimediaServicio;

    //Transferir archivo, por el dto se comparte la relacion y el id de la relacion
    @PostMapping("transferirarchivos")
    public ResponseEntity<?> transferirArchivos(
            @RequestPart("archivos") List<MultipartFile> archivos,
            @RequestPart("dto") String dtoString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ArchivoMultimediaDTO dto = mapper.readValue(dtoString, ArchivoMultimediaDTO.class);
        System.out.println(dto);
        try {
            // Procesar archivos y DTO
            return ResponseEntity.ok(archivoMultimediaServicio.transferirArchivos(archivos, dto));
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body("DTO inválido");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("obtenerporid/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        try {
            ArchivoMultimedia archivo = archivoMultimediaServicio.obtenerPorId(id);
            return ResponseEntity.ok(archivo);

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Archivo no encontrado con ID: " + id);

        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: " + e.getMessage());
        }
    }

    @GetMapping("obtenerporrelacion/{relacion}/{id}")
    public ResponseEntity<?> obtenerPorRelacion(@PathVariable String relacion, @PathVariable Long id) {
        try {
            List<ArchivoMultimediaInterfaz> resultado = switch (relacion.toLowerCase()) {
                case "publicacion" -> archivoMultimediaServicio.listarArchivosPorPublicacion(id);
                case "producto" -> archivoMultimediaServicio.listarArchivosPorProducto(id);
                case "categoria" -> archivoMultimediaServicio.listarArchivosPorCategoria(id);
                case "usuario" -> archivoMultimediaServicio.listarArchivosPorUsuario(id);
                default -> throw new IllegalArgumentException("Relación no válida: " + relacion);
            };
            return ResponseEntity.ok(resultado);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: " + e.getMessage());

        }
    }

    @DeleteMapping("eliminarporid/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable Long id) {
        try {
            archivoMultimediaServicio.eliminarPorId(id);
            return ResponseEntity.ok("Archivo eliminado correctamente con ID: " + id);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el archivo a eliminar con ID: " + id);

        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar archivo físico: " + e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: " + e.getMessage());
        }
    }
}
