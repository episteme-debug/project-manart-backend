package com.example.demo.DTOs;

public class LogInDTO {
    String emailUsuario;
    String contrasenaUsuario;

    public LogInDTO() {
    }

    public LogInDTO(String emailUsuario, String contrasenaUsuario) {
        this.emailUsuario = emailUsuario;
        this.contrasenaUsuario = contrasenaUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getContrasenaUsuario() {
        return contrasenaUsuario;
    }

    public void setContrasenaUsuario(String contrasenaUsuario) {
        this.contrasenaUsuario = contrasenaUsuario;
    }

    @Override
    public String toString() {
        return "logInDTO{" +
                "emailUsuario='" + emailUsuario + '\'' +
                ", contrasenaUsuario='" + contrasenaUsuario + '\'' +
                '}';
    }
}
