package com.example.demo.Controladores;

import com.example.demo.Servicios.ArchivoMultimediaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin("http://127.0.0.1:5500/")
@RequestMapping("/api/ArchivoMultimedia")
@RestController
public class ArchivoMultimediaControlador {
    @Autowired
    ArchivoMultimediaServicio archivoMultimediaServicio;

    //Transferir archivo
    @PostMapping("transferirArchivo/")
    public void transferirArchivo(@RequestParam MultipartFile archivo) throws IOException {
        archivoMultimediaServicio.transferirArchivo(archivo);
    }
}
