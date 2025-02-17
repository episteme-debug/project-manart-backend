package com.example.demo.Controladores;

import com.example.demo.DTOs.LogInDTO;
import com.example.demo.Entidades.*;
import com.example.demo.Mensajes.MensajeLogIn;
import com.example.demo.Repositorios.UsuarioRepositorio;
import com.example.demo.Servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://127.0.0.1:5501/")
public class UsuarioControlador {

    @Autowired
    UsuarioServicio UsuarioServicio;

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @GetMapping("/getAllUsuarios")
    public List<Usuario> getAllUsuarios(){
        return UsuarioServicio.getAllUsuarios();
    }

    @GetMapping("/getUsuarioById/{id}")
    public Optional<Usuario> getUsuario(@PathVariable Integer id){
        return UsuarioServicio.getUsuarioById(id);
    }

    @PostMapping("/saveUsuario")
    public MensajeLogIn saveUsuario(@RequestBody Usuario objetoUsuario){
        return UsuarioServicio.saveUsuario(objetoUsuario);
    }

    @PostMapping("/logIn")
    public MensajeLogIn logInPersona(@RequestBody LogInDTO dataPersona){
        return UsuarioServicio.logInPersona(dataPersona);
    }
}
