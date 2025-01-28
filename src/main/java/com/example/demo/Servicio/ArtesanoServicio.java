package com.example.demo.Servicio;

import com.example.demo.Entidades.Artesano;
import com.example.demo.Repositorios.ArtesanoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArtesanoServicio {

    @Autowired
    private ArtesanoRepositorio artesanoRepositorio;

    //Obtener artesano por Id
    public Optional<Artesano> getArtesanoById(Integer Id){
        return artesanoRepositorio.findById(Id);
    }
    //
}
