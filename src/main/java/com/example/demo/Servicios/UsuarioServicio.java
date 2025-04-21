package com.example.demo.Servicios;

import com.example.demo.DTOs.ContraseñaUsuarioDTO;
import com.example.demo.DTOs.LogInDTO;
import com.example.demo.DTOs.UsuarioDTO;
import com.example.demo.Entidades.CarritoCompra;
import com.example.demo.Entidades.Usuario;
import com.example.demo.Enums.UsuarioEnum;
import com.example.demo.Repositorios.CarritoCompraRepositorio;
import com.example.demo.Repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsuarioServicio {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Autowired
    CarritoCompraServicio carritoCompraServicio;

    //. Crear un usuario
    public Usuario crearUsuario(Usuario usuario) {
        if (usuario == null || usuario.getNombreUsuario() == null) {
            throw new IllegalArgumentException("Los datos del usuario son inválidos.");
        }

        if (usuarioRepositorio.existsByNombreUsuario(usuario.getNombreUsuario())) {
            throw new IllegalArgumentException("El nombre de usuario ya está en uso.");
        }

        Usuario usuarioCreado = usuarioRepositorio.save(usuario);
        // Crear un carrito asociado a este usuario
        CarritoCompra carritoCompra = new CarritoCompra();
        carritoCompra.setUsuario(usuarioCreado);
        carritoCompraServicio.crearCarrito(carritoCompra);
        return usuarioCreado;

    }

    //. Obtener usuario por Id
    public Optional<Usuario> obtenerPorId(Long idUsuario) {
        if (idUsuario == null || idUsuario <= 0) {
            throw new IllegalArgumentException("ID de usuario inválido.");
        }

        return usuarioRepositorio.findById(idUsuario);
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
    public Usuario actualizarDatos(UsuarioDTO usuarioDTO) {
        if (usuarioDTO == null || usuarioDTO.getIdUsuario() == null) {
            throw new IllegalArgumentException("Los datos del usuario son inválidos.");
        }

        Optional<Usuario> usuarioOptional = usuarioRepositorio.findById(usuarioDTO.getIdUsuario());
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

        if (usuarioDTO.getImagenPerfilUsuario() != null) {
            usuario.setImagenPerfilUsuario(usuarioDTO.getImagenPerfilUsuario());
        }

        if (usuarioDTO.getEstadoUsuario() != null) {
            usuario.setEstadoUsuario(usuarioDTO.getEstadoUsuario());
        }

        if (usuarioDTO.getRolUsuario() != null) {
            usuario.setRolUsuario(usuarioDTO.getRolUsuario());
        }

        return usuarioRepositorio.save(usuario);
    }

    //. Actualizar Contraseña
    public Usuario actualizarContraseña(ContraseñaUsuarioDTO dto) {
        if (dto == null || dto.getIdUsuario() == null || dto.getContraseñaAntigua() == null || dto.getContraseñaNueva() == null) {
            throw new IllegalArgumentException("Datos de contraseña inválidos.");
        }

        Optional<Usuario> usuarioOptional = usuarioRepositorio.findById(dto.getIdUsuario());
        if (!usuarioOptional.isPresent()) {
            throw new NoSuchElementException("Usuario no encontrado.");
        }
        Usuario usuario = usuarioOptional.get();

        if (!dto.getContraseñaAntigua().equals(usuario.getHashContrasenaUsuario())) {
            throw new IllegalArgumentException("Contraseña antigua incorrecta.");
        }

        usuario.setHashContrasenaUsuario(dto.getContraseñaNueva());
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


/*    //LogIn Persona
    public MensajeLogIn logInPersona(LogInDTO dataPersona) {
        MensajeLogIn dataMessage = new MensajeLogIn();
        String emailUsuario = dataPersona.getEmailUsuario();
        String contrasenaUsuario = dataPersona.getContrasenaUsuario();
        Optional<Usuario> personaExiste = usuarioRepositorio.findByEmailUsuario(emailUsuario);

        if (personaExiste.isPresent()) {
            Usuario usuario = personaExiste.get();
            if (usuario.getHashContrasenaUsuario().equals(contrasenaUsuario)) {
                //Ingresos correctos
                dataMessage.setMensaje("Inicio de Sesión exitoso!");
                dataMessage.setError(2);
                dataMessage.setUsuario(usuario);
                return dataMessage;
            } else {
                //Password incorrecta
                dataMessage.setMensaje("Contraseña Incorrecta." + " Por favor verifique sus datos.");
                dataMessage.setError(1);
                dataMessage.setUsuario(null);
                return dataMessage;
            }
        }
        //Usuario no encontrado
        dataMessage.setMensaje("Email Incorrecto." + " Por favor verifique sus datos.");
        dataMessage.setError(0);
        dataMessage.setUsuario(null);
        return dataMessage;
    }*/
}
