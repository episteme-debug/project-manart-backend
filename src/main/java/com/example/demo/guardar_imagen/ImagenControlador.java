package com.example.demo.guardar_imagen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("http://127.0.0.1:5500/")
public class ImagenControlador {

    private static final String UPLOAD_DIR = "D:\\SENA_2025\\proyectos_practica\\carpeta_imagenes";

    @Autowired
    imagenServicio imagenServicio;

    @PostMapping("/upload")
    public Map<String, String> uploadImage(@RequestParam("file") MultipartFile file) {
        Map<String, String> response = new HashMap<>();

        if (file.isEmpty()) {
            response.put("error", "No se seleccionó ningún archivo.");
            return response;
        }

        try {
            // Crear el directorio si no existe
            //Sirve para defirir el lugar donde el objeto de tipo file que representa un directorio en el sistema de archivos se guardará
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            // Guardar el archivo en la carpeta local
            String filePath = UPLOAD_DIR + File.separator + file.getOriginalFilename();
            file.transferTo(Paths.get(filePath));
            imagenServicio.guardarImagen(file.getOriginalFilename());

            response.put("message", "Imagen guardada exitosamente.");
            response.put("path", filePath);

        } catch (IOException e) {
            e.printStackTrace();
            response.put("error", "Error al guardar la imagen.");
        }

        return response;
    }

}
