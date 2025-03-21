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
    @PostMapping("/obtenerUsuarioPorId")
    public ResponseEntity<?> obtenerUsuarioPorId(@RequestBody UsuarioDTO usuarioDTO)
    {
        Long idUsuario = usuarioDTO.getIdUsuario();
        Optional<Usuario> optionalUsuario = usuarioServicio.obtenerUsuarioPorId(idUsuario);

        return ResponseEntity.ok(optionalUsuario.get());

    }

    //3. Verificar la existencia de un usuario con un determinado username
    @PostMapping("/verificarNombreUsuario")
    public boolean verificarNombreUsuario(@RequestBody UsuarioDTO usuarioDTO)
    {
        String username = usuarioDTO.getNombreUsuario();
        boolean usuarioExistentes = usuarioServicio.verificarNombreUsuario(username);

        return usuarioExistentes;
    }

    //4. Obtener usuarios por estado
    @PostMapping("/obtenerUsuariosPorEstado")
    public List<Usuario> obtenerUsuariosPorEstado(@RequestBody UsuarioDTO usuarioDTO)
    {
        boolean estadoUsuario = usuarioDTO.getEstadoUsuario();
        List<Usuario> listaUsuarios = usuarioServicio.obtenerUsuariosPorEstado(estadoUsuario);

        System.out.println(estadoUsuario);
        return listaUsuarios;
    }

    //5. Obtener usuarios por rol
    @PostMapping("/obtenerUsuariosPorRol")
    public List<?> obtenerUsuariosPorRol(@RequestBody UsuarioDTO usuarioDTO)
    {
        UsuarioEnum rolUsuario = usuarioDTO.getRolUsuario();
        List<?> listaUsuarios = usuarioServicio.obtenerUsuariosPorRol(rolUsuario);

        return listaUsuarios;
    }

    //6. Actualizar datos de usuario excepto la contraseña
    @PostMapping("/actualizarDatosUsuario")
    public Usuario actualizarDatosUsuario(@RequestBody UsuarioDTO usuarioDTO){
        return usuarioServicio.actualizarDatosUsuario(usuarioDTO);
    }

    //7. Actualizar contraseña de usuario
    @PostMapping("/actualizarContraseña")
    public Usuario actualizarContrasela(@RequestBody ContraseñaUsuarioDTO usuarioDTO){
        return usuarioServicio.actualizarContraseña(usuarioDTO);
    }

    //8. Eliminar usuario por id
    @PostMapping("/eliminarUsuarioPorId")
    public List<?> eliminarUsuarioPorId(@RequestBody UsuarioDTO usuarioDTO){
        return usuarioServicio.eliminarUsuario(usuarioDTO);
    }

/*

    @PostMapping("/logIn")
    public MensajeLogIn logInPersona(@RequestBody LogInDTO dataPersona){
        return UsuarioServicio.logInPersona(dataPersona);
    }*/
}
