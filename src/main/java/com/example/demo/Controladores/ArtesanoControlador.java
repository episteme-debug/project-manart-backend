package com.example.demo.Controladores;

import com.example.demo.Entidades.Artesano;
import com.example.demo.Servicios.ArtesanoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500/")
public class ArtesanoControlador {


    @Autowired
    private ArtesanoServicio artesanoServicio;

    //Get artesano By Id
    @GetMapping("getArtesanoById/{Id}")
    public Optional<Artesano> getArtesanoById(@PathVariable Integer Id){
        return artesanoServicio.getArtesanoById(Id);
    }

}
