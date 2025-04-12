package com.example.demo.Servicios;

import com.example.demo.Entidades.ArchivoMultimedia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ArchivoMultimediaServicio {

    @Autowired

    private static final String dirPersonal = "D:\\Manart\\backend\\";
    private static final String dirGeneral = "project-manart-backend\\src\\main\\resources\\static\\subido\\";
    private static final String dirImagenes = dirPersonal + dirGeneral + "imagen";


    //. Tranferir archivo subido al directorio
    public void transferirArchivo(MultipartFile archivo) throws IOException {
        File dirImagen = new File(dirImagenes);

        String rutaArchivo = dirImagen + File.separator + archivo.getOriginalFilename();
        archivo.transferTo(Paths.get(rutaArchivo));

    }

    public String identificarTipoArchivo(Path rutaArchivo) throws IOException {
        String tipoMime = Files.probeContentType(rutaArchivo);
        return tipoMime;
    }


/*    //. Guardar archivo en base de datos
    public void guardarArchivo(String entidad, Long objetoEntidad, String nombreArchivo, String rutaArchivo){
        ArchivoMultimedia archivoMultimedia = new ArchivoMultimedia();

        archivoMultimedia.s

    }*/
}