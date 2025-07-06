package com.example.demo.Controladores;

import com.example.demo.DTOs.UsuarioDTO.ActualizacionContraseñaUsuario;
import com.example.demo.DTOs.UsuarioDTO.RespuestaUsuario;
import com.example.demo.DTOs.UsuarioDTO.ActualizacionUsuario;
import com.example.demo.Enums.UsuarioEnum;
import com.example.demo.Servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
            RespuestaUsuario usuario = usuarioServicio.obtenerPorId(id);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //. Obtener usuarios por estado
    @GetMapping("private/listarusuarios")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> listarUsuarios() {
        try{
            List<RespuestaUsuario> usuarios = usuarioServicio.listarUsuarios();
            return ResponseEntity.ok(usuarios);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    //. Obtener usuarios por estado
    @GetMapping("private/obtenerporestado/{estado}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> obtenerPorEstado(@PathVariable Boolean estado) {
        try{
            List<RespuestaUsuario> usuarios = usuarioServicio.obtenerPorEstado(estado);
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
            List<RespuestaUsuario> usuarios = usuarioServicio.obtenerUsuariosPorRol(rol);
            return ResponseEntity.ok(usuarios);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //. Actualizar datos de usuario excepto la contraseña
    @PatchMapping("private/actualizardatos/{idUsuario}")
    @PreAuthorize("@autorizacion.esPropietario(#idUsuario)")
    public ResponseEntity<?> actualizarDatos(@RequestBody ActualizacionUsuario dto, @PathVariable Long idUsuario) {
        try {
            RespuestaUsuario actualizado = usuarioServicio.actualizarDatos(dto, idUsuario);
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException | NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //. Actualizar contraseña de usuario
    @PatchMapping("private/actualizarcontraseña/{idUsuario}")
    @PreAuthorize("@autorizacion.esPropietario(#idUsuario)")
    public ResponseEntity<?> actualizarContrasena(@RequestBody ActualizacionContraseñaUsuario dto, @PathVariable Long idUsuario) {
        try {
            String actualizado = usuarioServicio.actualizarContraseña(dto, idUsuario);
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
            String eliminacion = usuarioServicio.eliminarUsuario(idUsuario);
            return ResponseEntity.ok(eliminacion);
        } catch (IllegalArgumentException | NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //. Obtener usuario en sesión
    @GetMapping("private/cargar-usuario")
    public ResponseEntity<?> verificarSesion() {a
        try {
            return ResponseEntity.ok(usuarioServicio.obtenerUsuarioAutenticado());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Sin sesión");
        }
    }

}
