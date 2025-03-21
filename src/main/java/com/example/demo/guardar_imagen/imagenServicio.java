package com.example.demo.guardar_imagen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class imagenServicio {

    @Autowired
    imagenRepositorio imagenRepositorio;

    public void guardarImagen(String nameFile)
    {
        imagen imagen = new imagen(nameFile);
        imagenRepositorio.save(imagen);
    }
}
