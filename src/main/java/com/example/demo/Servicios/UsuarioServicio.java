package com.example.demo.Servicios;

import com.example.demo.DTOs.ContraseñaUsuarioDTO;
import com.example.demo.DTOs.LogInDTO;
import com.example.demo.DTOs.UsuarioDTO;
import com.example.demo.Entidades.CarritoCompra;
import com.example.demo.Entidades.Usuario;
import com.example.demo.Enums.UsuarioEnum;
import com.example.demo.Repositorios.CarritoCompraRepositorio;
import com.example.demo.Repositorios.UsuarioRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsuarioServicio {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Autowired
    CarritoCompraServicio carritoCompraServicio;

    @Autowired
    PasswordEncoder passwordEncoder;

    //. Obtener usuario por Id
    public UsuarioDTO obtenerPorId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID de usuario inválido.");
        }

        Usuario usuario = usuarioRepositorio.findById(id).get();
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);

        return usuarioDTO;
    }

    //. Obtener usuarios por estado (activo/inactivo)
    public List<Usuario> obtenerPorEstado(Boolean estado) {
        return usuarioRepositorio.findByEstadoUsuario(estado);
    }

    //5. Obtener usuarios por rol (ADMIN / COMPRADOR / VENDEDOR / ORGANIZADOR)
    public List<Usuario> obtenerUsuariosPorRol(UsuarioEnum rolUsuario) {
        if (rolUsuario == null ) {
            throw new IllegalArgumentException("El rol de usuario no puede ser nulo.");
        } else if (!UsuarioEnum.existe(String.valueOf(rolUsuario))) {
            throw new IllegalArgumentException("El rol de usuario es inválido.");
        }

        return usuarioRepositorio.findByRolUsuario(rolUsuario);
    }

    //. Actualizar datos de usuario
    public Usuario actualizarDatos(UsuarioDTO usuarioDTO, Long idUsuario) {
        if (usuarioDTO == null) {
            throw new IllegalArgumentException("Los datos del usuario son inválidos.");
        }

        Optional<Usuario> usuarioOptional = usuarioRepositorio.findById(idUsuario);
        if (!usuarioOptional.isPresent()) {
            throw new NoSuchElementException("Usuario no encontrado.");
        }
        Usuario usuario = usuarioOptional.get();

        if (usuarioDTO.getNombreUsuario() != null) {
            usuario.setNombreUsuario(usuarioDTO.getNombreUsuario());
        }

        if (usuarioDTO.getApellidoUsuario() != null) {
            usuario.setApellidoUsuario(usuarioDTO.getApellidoUsuario());
        }

        if (usuarioDTO.getEmailUsuario() != null) {
            usuario.setEmailUsuario(usuarioDTO.getEmailUsuario());
        }

        if (usuarioDTO.getTelefonoUsuario() != null) {
            usuario.setTelefonoUsuario(usuarioDTO.getTelefonoUsuario());
        }

        if (usuarioDTO.getEstadoUsuario() != null) {
            usuario.setEstadoUsuario(usuarioDTO.getEstadoUsuario());
        }

        if (usuarioDTO.getRolUsuario() != null) {
            usuario.setRolUsuario(usuarioDTO.getRolUsuario());
        }

        if (usuarioDTO.getAlias() != null) {
            usuario.setAlias(usuarioDTO.getAlias());
        }

        return usuarioRepositorio.save(usuario);
    }

    //. Actualizar Contraseña
    public Usuario actualizarContraseña(ContraseñaUsuarioDTO dto, Long id) {
        if (dto == null || dto.getContraseñaAntigua() == null || dto.getContraseñaNueva() == null) {
            throw new IllegalArgumentException("Datos de contraseña inválidos.");
        }

        Optional<Usuario> usuarioOptional = usuarioRepositorio.findById(id);
        if (!usuarioOptional.isPresent()) {
            throw new NoSuchElementException("Usuario no encontrado.");
        }
        Usuario usuario = usuarioOptional.get();

        if (!passwordEncoder.matches(dto.getContraseñaAntigua(), usuario.getHashContrasenaUsuario())) {
            throw new IllegalArgumentException("Contraseña antigua incorrecta.");
        }
        usuario.setHashContrasenaUsuario(passwordEncoder.encode(dto.getContraseñaNueva()));
        return usuarioRepositorio.save(usuario);
    }

    //8. Eliminar usuario
    public void eliminarUsuario(Long idUsuario) {
        if (idUsuario == null || idUsuario <= 0) {
            throw new IllegalArgumentException("ID inválido para eliminar.");
        }

        if (!usuarioRepositorio.existsById(idUsuario)) {
            throw new NoSuchElementException("Usuario no encontrado para eliminar.");
        }

        usuarioRepositorio.deleteById(idUsuario);
    }

    //Obtener todos los usuarios
    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioRepositorio.findAll();
    }

}
