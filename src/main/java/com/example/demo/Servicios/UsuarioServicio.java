package com.example.demo.Servicios;

import com.example.demo.DTOs.UsuarioDTO.ActualizacionContraseñaUsuario;
import com.example.demo.DTOs.UsuarioDTO.RespuestaUsuario;
import com.example.demo.DTOs.UsuarioDTO.ActualizacionUsuario;
import com.example.demo.Entidades.ArchivoMultimedia;
import com.example.demo.Entidades.Usuario;
import com.example.demo.Enums.EntidadesArchivoMultimediaEnum;
import com.example.demo.Enums.UsuarioEnum;
import com.example.demo.Repositorios.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class UsuarioServicio {

    private final ArchivoMultimediaServicio archivoMultimediaServicio;
    private final PasswordEncoder passwordEncoder;
    private final ProductoServicio productoServicio;
    private final UsuarioRepositorio usuarioRepositorio;

    //. Obtener usuario por Id
    public RespuestaUsuario obtenerPorId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID de usuario inválido.");
        }

        Usuario usuario = usuarioRepositorio.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        return generarRespuesta(usuario);
    }

    //. Obtener usuarios por estado (activo/inactivo)
    public List<RespuestaUsuario> obtenerPorEstado(Boolean estado) {
        List<Usuario> usuarios = usuarioRepositorio.findByEstadoUsuario(estado);
        List<RespuestaUsuario> usuariosRespuesta = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            RespuestaUsuario respuesta = generarRespuesta(usuario);
            usuariosRespuesta.add(respuesta);
        }

        return usuariosRespuesta;
    }

    //5. Obtener usuarios por rol (ADMIN / COMPRADOR / VENDEDOR)
    public List<RespuestaUsuario> obtenerUsuariosPorRol(UsuarioEnum rolUsuario) {
        if (rolUsuario == null) {
            throw new IllegalArgumentException("El rol de usuario no puede ser nulo.");
        } else if (!UsuarioEnum.existe(String.valueOf(rolUsuario))) {
            throw new IllegalArgumentException("El rol de usuario es inválido.");
        }

        List<Usuario> usuarios = usuarioRepositorio.findByRolUsuario(rolUsuario);
        List<RespuestaUsuario> usuariosRespuesta = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            RespuestaUsuario respuesta = generarRespuesta(usuario);
            usuariosRespuesta.add(respuesta);
        }

        return usuariosRespuesta;
    }

    //. Actualizar datos de usuario
    public RespuestaUsuario actualizarDatos(ActualizacionUsuario actualizacionUsuario, Long idUsuario) {
        if (actualizacionUsuario == null) {
            throw new IllegalArgumentException("Los datos del usuario son inválidos.");
        }

        Usuario usuario = usuarioRepositorio.findById(idUsuario)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        if (actualizacionUsuario.getAlias() != null) {
            usuario.setAlias(actualizacionUsuario.getAlias());
        }

        if (actualizacionUsuario.getNombreUsuario() != null) {
            usuario.setNombreUsuario(actualizacionUsuario.getNombreUsuario());
        }

        if (actualizacionUsuario.getApellidoUsuario() != null) {
            usuario.setApellidoUsuario(actualizacionUsuario.getApellidoUsuario());
        }

        if (actualizacionUsuario.getEmailUsuario() != null) {
            usuario.setEmailUsuario(actualizacionUsuario.getEmailUsuario());
        }

        if (actualizacionUsuario.getTelefonoUsuario() != null) {
            usuario.setTelefonoUsuario(actualizacionUsuario.getTelefonoUsuario());
        }

        if (actualizacionUsuario.getEstadoUsuario() != null) {
            usuario.setEstadoUsuario(actualizacionUsuario.getEstadoUsuario());
        }

        Usuario usuarioActualizado = usuarioRepositorio.save(usuario);

        return generarRespuesta(usuarioActualizado);
    }

    //. Actualizar Contraseña
    public String actualizarContraseña(ActualizacionContraseñaUsuario dto, Long idUsuario) {
        if (dto == null || dto.getContraseñaAntigua() == null || dto.getContraseñaNueva() == null) {
            throw new IllegalArgumentException("Datos de contraseña inválidos.");
        }

        Usuario usuario = usuarioRepositorio.findById(idUsuario)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        if (!passwordEncoder.matches(dto.getContraseñaAntigua(), usuario.getHashContrasenaUsuario())) {
            throw new IllegalArgumentException("Contraseña antigua incorrecta.");
        }
        usuario.setHashContrasenaUsuario(passwordEncoder.encode(dto.getContraseñaNueva()));

        usuarioRepositorio.save(usuario);
        return "La contraseña cambió con éxito";
    }

    //8. Eliminar usuario
    public String eliminarUsuario(Long idUsuario) {
        if (idUsuario == null || idUsuario <= 0) {
            throw new IllegalArgumentException("ID inválido para eliminar.");
        }

        if (!usuarioRepositorio.existsById(idUsuario)) {
            throw new NoSuchElementException("Usuario no encontrado para eliminar.");
        }

        usuarioRepositorio.deleteById(idUsuario);
        return "El usuario se eliminó con éxito";
    }

    //Obtener todos los usuarios
    public List<RespuestaUsuario> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        List<RespuestaUsuario> usuariosRespuesta = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            RespuestaUsuario respuesta = generarRespuesta(usuario);
            usuariosRespuesta.add(respuesta);
        }

        return usuariosRespuesta;
    }

    public Usuario obtenerPorAlias(String alias) {
        Usuario usuario = usuarioRepositorio.findByAlias(alias)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        return usuario;
    }

    public Usuario obtenerDetalleUsuario() {
        String alias = SecurityContextHolder.getContext().getAuthentication().getName();

        return obtenerPorAlias(alias);
    }

    public RespuestaUsuario generarRespuesta (Usuario usuario) {
        RespuestaUsuario respuesta = new RespuestaUsuario();
        respuesta.setIdUsuario(usuario.getIdUsuario());
        respuesta.setAlias(usuario.getAlias());
        respuesta.setNombreUsuario(usuario.getNombreUsuario());
        respuesta.setApellidoUsuario(usuario.getApellidoUsuario());
        respuesta.setEmailUsuario(usuario.getEmailUsuario());
        respuesta.setTelefonoUsuario(usuario.getTelefonoUsuario());
        respuesta.setEstadoUsuario(usuario.getEstadoUsuario());
        respuesta.setRolUsuario(usuario.getRolUsuario());

        List<ArchivoMultimedia> archivos = archivoMultimediaServicio.listarArchivosPorEntidadYId(EntidadesArchivoMultimediaEnum.Usuario, usuario.getIdUsuario());

        respuesta.setListaArchivos(archivos);
        respuesta.setListaProductos(productoServicio.listarPorUsuario(usuario.getIdUsuario()));

        return respuesta;
    }

}
