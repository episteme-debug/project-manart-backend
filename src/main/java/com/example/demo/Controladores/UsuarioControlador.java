package com.example.demo.Controladores;

import com.example.demo.DTOs.ContraseñaUsuarioDTO;
import com.example.demo.DTOs.LogInDTO;
import com.example.demo.DTOs.UsuarioDTO;
import com.example.demo.Entidades.*;
import com.example.demo.Enums.UsuarioEnum;
import com.example.demo.Mensajes.MensajeLogIn;
import com.example.demo.Repositorios.UsuarioRepositorio;
import com.example.demo.Servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin("http://127.0.0.1:5500/")
public class UsuarioControlador {

    @Autowired
    UsuarioServicio usuarioServicio;

    //1. Crear un usuario (Este controlador recibe un usuario y devuelve un usuario un codigo de estado y un mensaje)
    @PostMapping("/guardarUsuario")
    public Usuario guardarUsuario(@RequestBody Usuario usuario){
        return usuarioServicio.guardarUsuario(usuario);
    }

    //2. Obtener un usuario con un respectivo id
    @GetMapping("/obtenerUsuarioPorId/{idUsuario}")
    public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable Long idUsuario)
    {
        Optional<Usuario> optionalUsuario = usuarioServicio.obtenerUsuarioPorId(idUsuario);

        return ResponseEntity.ok(optionalUsuario.get());

    }

    //3. Verificar la existencia de un usuario con un determinado username
    @GetMapping("/verificarNombreUsuario/{nombreUsuario}")
    public boolean verificarNombreUsuario(@PathVariable String nombreUsuario)
    {
        return usuarioServicio.verificarNombreUsuario(nombreUsuario);

    }

    //4. Obtener usuarios por estado
    @GetMapping("/obtenerUsuariosPorEstado/{estadoUsuario}")
    public List<Usuario> obtenerUsuariosPorEstado(@PathVariable Boolean estadoUsuario)
    {
        return usuarioServicio.obtenerUsuariosPorEstado(estadoUsuario);
    }

    //5. Obtener usuarios por rol
    @GetMapping("/obtenerUsuariosPorRol/{rolUsuario}")
    public List<?> obtenerUsuariosPorRol(@PathVariable UsuarioEnum rolUsuario)
    {
        return usuarioServicio.obtenerUsuariosPorRol(rolUsuario);
    }

    //6. Actualizar datos de usuario excepto la contraseña
    @PatchMapping("/actualizarDatosUsuario")
    public Usuario actualizarDatosUsuario(@RequestBody UsuarioDTO usuarioDTO){
        return usuarioServicio.actualizarDatosUsuario(usuarioDTO);
    }

    //7. Actualizar contraseña de usuario
    @PatchMapping("/actualizarContraseña")
    public Usuario actualizarContrasela(@RequestBody ContraseñaUsuarioDTO usuarioDTO){
        return usuarioServicio.actualizarContraseña(usuarioDTO);
    }

    //8. Eliminar usuario por id
    @DeleteMapping("/eliminarUsuarioPorId/{idUsuario}")
    public List<?> eliminarUsuarioPorId(@PathVariable Long idUsuario){
        return usuarioServicio.eliminarUsuario(idUsuario);
    }

/*

    @PostMapping("/logIn")
    public MensajeLogIn logInPersona(@RequestBody LogInDTO dataPersona){
        return UsuarioServicio.logInPersona(dataPersona);
    }*/
}
