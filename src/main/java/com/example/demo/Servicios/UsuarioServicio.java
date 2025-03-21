package com.example.demo.Servicios;

import com.example.demo.DTOs.ContraseñaUsuarioDTO;
import com.example.demo.DTOs.LogInDTO;
import com.example.demo.DTOs.UsuarioDTO;
import com.example.demo.Entidades.Usuario;
import com.example.demo.Enums.UsuarioEnum;
import com.example.demo.Mensajes.MensajeLogIn;
import com.example.demo.Repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UsuarioServicio {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    //1. Crear un usuario
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    //2. Obtener usuario por Id
    public Optional<Usuario> obtenerUsuarioPorId(Long idUsuario) {
        return usuarioRepositorio.findById(idUsuario);
    }

    //3. Verificar la existencia de un usuario con un determinado username
    public boolean verificarNombreUsuario(String username) {
        return usuarioRepositorio.existsByNombreUsuario(username);
    }

    //4. Obtener usuarios por estado (activo/inactivo)
    public List<Usuario> obtenerUsuariosPorEstado(Boolean estadoUsuario) {
        return usuarioRepositorio.findByEstadoUsuario(estadoUsuario);
    }

    //5. Obtener usuarios por rol (ADMIN / COMPRADOR / VENDEDOR / ORGANIZADOR)
    public List<Usuario> obtenerUsuariosPorRol(Enum<UsuarioEnum> rolUsuario) {
        return usuarioRepositorio.findByRolUsuario(rolUsuario);
    }

    //6. Actualizar datos de usuario
    public Usuario actualizarDatosUsuario(UsuarioDTO usuarioDTO) {
        //Instancia de usuario
        Long idUsuario = usuarioDTO.getIdUsuario();
        Usuario usuario = obtenerUsuarioPorId(idUsuario).get();

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

        if (usuarioDTO.getEstadoUsuario() != false) {
            usuario.setEstadoUsuario(usuarioDTO.getEstadoUsuario());
        }

        if (usuarioDTO.getRolUsuario() != null) {
            usuario.setRolUsuario(usuarioDTO.getRolUsuario());
        }

        return usuarioRepositorio.save(usuario);
    }

    //7. Actualizar Contraseña
    public Usuario actualizarContraseña(ContraseñaUsuarioDTO usuarioDTO) {
        Long idUsuario = usuarioDTO.getIdUsuario();
        String contraseñaAntigua = usuarioDTO.getContraseñaAntigua();
        String contraseñaNueva = usuarioDTO.getContraseñaNueva();
        Usuario usuario = usuarioRepositorio.findById(idUsuario).get();

        if (contraseñaAntigua.equals(usuario.getHashContrasenaUsuario())) {
            usuario.setHashContrasenaUsuario(contraseñaNueva);
            usuarioRepositorio.save(usuario);
        }
        return usuario;
    }

    //8. Eliminar usuario
    public List<?> eliminarUsuario(UsuarioDTO usuarioDTO) {
        Long idUsuario = usuarioDTO.getIdUsuario();
        usuarioRepositorio.deleteById(idUsuario);

        return obtenerTodosUsuarios();
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
