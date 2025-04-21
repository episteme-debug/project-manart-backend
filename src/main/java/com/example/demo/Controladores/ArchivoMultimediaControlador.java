package com.example.demo.Controladores;

import com.example.demo.DTOs.ArchivoMultimediaDTO;
import com.example.demo.Entidades.ArchivoMultimedia;
import com.example.demo.Interfaces.ArchivoMultimediaInterfaz;
import com.example.demo.Servicios.ArchivoMultimediaServicio;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@CrossOrigin("http://127.0.0.1:5500/")
@RequestMapping("/api/archivomultimedia/")
@RestController
public class ArchivoMultimediaControlador {
    @Autowired
    ArchivoMultimediaServicio archivoMultimediaServicio;

    //Transferir archivo, por el dto se comparte la relacion y el id de la relacion
    @PostMapping("transferirarchivos")
    public List<ArchivoMultimedia> transferirArchivos(@RequestParam("archivos") List<MultipartFile> archivos, @RequestParam("dtoString") String dtoString) throws IOException {
        ObjectMapper conversion = new ObjectMapper();
        ArchivoMultimediaDTO dtoJava = conversion.readValue(dtoString, ArchivoMultimediaDTO.class);

        return archivoMultimediaServicio.transferirArchivos(archivos, dtoJava);
    }


    @GetMapping("obtenerporid/{id}")
    public ArchivoMultimedia obtenerPorId(@PathVariable Long id) {
        return archivoMultimediaServicio.obtenerPorId(id);
    }

    @GetMapping("obtenerporrelacion/{relacion}/{id}")
    public List<ArchivoMultimediaInterfaz> obtenerPorRelacion(@PathVariable String relacion, @PathVariable Long id) {
        return switch (relacion.toLowerCase()) {
            case "publicacion" -> archivoMultimediaServicio.listarArchivosPorPublicacion(id);
            case "producto" -> archivoMultimediaServicio.listarArchivosPorProducto(id);
            case "categoria" -> archivoMultimediaServicio.listarArchivosPorCategoria(id);
            case "usuario" -> archivoMultimediaServicio.listarArchivosPorUsuario(id);
            default -> Collections.emptyList(); // mejor que return null
        };
    }


    @DeleteMapping("eliminarporid/{id}")
    public void eliminarPorId(@PathVariable Long id) throws IOException {
        archivoMultimediaServicio.eliminarPorId(id);
        ;
    }
}
