package com.example.demo.Servicio;

import com.example.demo.Entidades.Usuario;
import com.example.demo.Repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    //Get All Usuarios
    public List<Usuario> getAllUsuarios(){
        return usuarioRepositorio.findAll();
    }

    //Get a Usuario By Id
    public Optional<Usuario> getUsuarioById(Integer id){
        return usuarioRepositorio.findById(id);
    }

    //Insert a Usuario in db
    public Usuario saveUsuario(Usuario objeto){
        return usuarioRepositorio.save(objeto);
    }

}
