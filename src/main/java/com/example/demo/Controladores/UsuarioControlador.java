package com.example.demo.Controladores;

import com.example.demo.DTOs.ContraseñaUsuarioDTO;
import com.example.demo.DTOs.LogInDTO;
import com.example.demo.DTOs.UsuarioDTO;
import com.example.demo.Entidades.*;
import com.example.demo.Enums.UsuarioEnum;
import com.example.demo.Servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin("http://127.0.0.1:5500/")
public class UsuarioControlador {

    @Autowired
    UsuarioServicio usuarioServicio;

    //. Crear un usuario (Este controlador recibe un usuario y devuelve un usuario un codigo de estado y un mensaje)
    @PostMapping("/guardar")
    public ResponseEntity<?> crear(@RequestBody Usuario usuario) {
        try {
            Usuario nuevoUsuario = usuarioServicio.crearUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    //. Obtener un usuario con un respectivo id
    @GetMapping("/obtenerPorId/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        try {
            Optional<Usuario> usuario = usuarioServicio.obtenerPorId(id);
            return usuario.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //. Obtener usuarios por estado
    @GetMapping("/obtenerPorEstado/{estado}")
    public ResponseEntity<?> obtenerPorEstado(@PathVariable Boolean estado) {
        try{
            List<Usuario> usuarios = usuarioServicio.obtenerPorEstado(estado);
            return ResponseEntity.ok(usuarios);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    //. Obtener usuarios por rol
    @GetMapping("/obtenerPorRol/{rolUsuario}")
    public ResponseEntity<?> obtenerPorRol(@PathVariable UsuarioEnum rol) {
        try {
            List<Usuario> usuarios = usuarioServicio.obtenerUsuariosPorRol(rol);
            return ResponseEntity.ok(usuarios);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //. Actualizar datos de usuario excepto la contraseña
    @PatchMapping("/actualizarDatos")
    public ResponseEntity<?> actualizarDatos(@RequestBody UsuarioDTO dto) {
        try {
            Usuario actualizado = usuarioServicio.actualizarDatos(dto);
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException | NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //. Actualizar contraseña de usuario
    @PatchMapping("/actualizarContraseña")
    public ResponseEntity<?> actualizarContraseña(@RequestBody ContraseñaUsuarioDTO dto) {
        try {
            Usuario actualizado = usuarioServicio.actualizarContraseña(dto);
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException | NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //8. Eliminar usuario por id
    @DeleteMapping("/eliminarPorId/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        try {
            usuarioServicio.eliminarUsuario(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException | NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

/*

    @PostMapping("/logIn")
    public MensajeLogIn logInPersona(@RequestBody LogInDTO dataPersona){
        return UsuarioServicio.logInPersona(dataPersona);
    }*/
}
