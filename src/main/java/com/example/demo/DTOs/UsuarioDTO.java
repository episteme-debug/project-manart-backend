package com.example.demo.DTOs;

import com.example.demo.Entidades.Usuario;
import com.example.demo.Enums.UsuarioEnum;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private String nombreUsuario;
    private String apellidoUsuario;
    private String emailUsuario;
    private String hashContrasenaUsuario;
    private String telefonoUsuario;
    private Boolean estadoUsuario;
    private String imagenPerfilUsuario;
    private UsuarioEnum rolUsuario;
    private String alias;

    public UsuarioDTO(Usuario usuario) {
        this.nombreUsuario = usuario.getNombreUsuario();
        this.apellidoUsuario = usuario.getApellidoUsuario();
        this.emailUsuario = usuario.getEmailUsuario();
        this.hashContrasenaUsuario = usuario.getHashContrasenaUsuario();
        this.telefonoUsuario = usuario.getTelefonoUsuario();
        this.estadoUsuario = usuario.getEstadoUsuario();
        this.imagenPerfilUsuario = usuario.getImagenPerfilUsuario();
        this.rolUsuario = usuario.getRolUsuario();
        this.alias = usuario.getAlias();
    }
}
