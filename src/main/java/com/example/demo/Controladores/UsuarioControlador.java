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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin("http://127.0.0.1:5500/")
public class UsuarioControlador {

    @Autowired
    UsuarioServicio usuarioServicio;

    //. Obtener un usuario con un respectivo id
    @GetMapping("private/obtenerporid/{id}")
    @PreAuthorize("@autorizacion.esPropietario(#id) or hasRole('ADMIN')")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {

        try {
            UsuarioDTO usuario = usuarioServicio.obtenerPorId(id);
            return ResponseEntity.ok(usuario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //. Obtener usuarios por estado
    @GetMapping("private/obtenerPorEstado/{estado}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> obtenerPorEstado(@PathVariable Boolean estado) {
        try{
            List<Usuario> usuarios = usuarioServicio.obtenerPorEstado(estado);
            return ResponseEntity.ok(usuarios);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    //. Obtener usuarios por rol
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("private/obtenerporrol/{rol}")
    public ResponseEntity<?> obtenerPorRol(@PathVariable UsuarioEnum rol) {
        try {
            List<Usuario> usuarios = usuarioServicio.obtenerUsuariosPorRol(rol);
            return ResponseEntity.ok(usuarios);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //. Actualizar datos de usuario excepto la contraseña
    @PatchMapping("private/actualizardatos/{idUsuario}")
    @PreAuthorize("@autorizacion.esPropietario(#idUsuario)")
    public ResponseEntity<?> actualizarDatos(@RequestBody UsuarioDTO dto, @PathVariable Long idUsuario) {
        try {
            Usuario actualizado = usuarioServicio.actualizarDatos(dto, idUsuario);
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException | NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //. Actualizar contraseña de usuario
    @PatchMapping("private/actualizarcontraseña/{idUsuario}")
    @PreAuthorize("@autorizacion.esPropietario(#idUsuario)")
    public ResponseEntity<?> actualizarContraseña(@RequestBody ContraseñaUsuarioDTO dto, @PathVariable Long idUsuario) {
        try {
            Usuario actualizado = usuarioServicio.actualizarContraseña(dto, idUsuario);
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException | NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //. Eliminar usuario por id
    @DeleteMapping("private/eliminarporid/{idUsuario}")
    @PreAuthorize("@autorizacion.esPropietario(#idUsuario) or hasRole('ADMIN')")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long idUsuario) {
        try {
            usuarioServicio.eliminarUsuario(idUsuario);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException | NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
