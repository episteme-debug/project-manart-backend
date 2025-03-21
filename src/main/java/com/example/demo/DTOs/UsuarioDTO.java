package com.example.demo.DTOs;

import com.example.demo.Enums.UsuarioEnum;
import jakarta.persistence.Column;

public class UsuarioDTO {
    private Long idUsuario;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String emailUsuario;
    private String hashContrasenaUsuario;
    private String telefonoUsuario;
    private boolean estadoUsuario;
    private String imagenPerfilUsuario;
    private UsuarioEnum rolUsuario;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long idUsuario, String nombreUsuario, String apellidoUsuario, String emailUsuario, String hashContrasenaUsuario, String telefonoUsuario, boolean estadoUsuario, String imagenPerfilUsuario, UsuarioEnum rolUsuario) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.emailUsuario = emailUsuario;
        this.hashContrasenaUsuario = hashContrasenaUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.estadoUsuario = estadoUsuario;
        this.imagenPerfilUsuario = imagenPerfilUsuario;
        this.rolUsuario = rolUsuario;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getHashContrasenaUsuario() {
        return hashContrasenaUsuario;
    }

    public void setHashContrasenaUsuario(String hashContrasenaUsuario) {
        this.hashContrasenaUsuario = hashContrasenaUsuario;
    }

    public String getTelefonoUsuario() {
        return telefonoUsuario;
    }

    public void setTelefonoUsuario(String telefonoUsuario) {
        this.telefonoUsuario = telefonoUsuario;
    }

    public boolean getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(boolean estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public String getImagenPerfilUsuario() {
        return imagenPerfilUsuario;
    }

    public void setImagenPerfilUsuario(String imagenPerfilUsuario) {
        this.imagenPerfilUsuario = imagenPerfilUsuario;
    }

    public UsuarioEnum getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(UsuarioEnum rolUsuario) {
        this.rolUsuario = rolUsuario;
    }
}
