package com.example.demo.controlador;

import com.example.demo.DTO.logInDTO;
import com.example.demo.Entidades.*;
import com.example.demo.Messages.logMessage;
import com.example.demo.Repositorios.UsuarioRepositorio;
import com.example.demo.Servicio.UsuarioServicio;
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
    public logMessage saveUsuario(@RequestBody Usuario objetoUsuario){
        return UsuarioServicio.saveUsuario(objetoUsuario);
    }

    @PostMapping("/logIn")
    public logMessage logInPersona(@RequestBody logInDTO dataPersona){
        return UsuarioServicio.logInPersona(dataPersona);
    }
}
