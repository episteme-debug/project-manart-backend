package com.example.demo.Entidades;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class CredencialesAcceso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column(nullable = false, length = 200)
    private String emailUsuario;

    @Column(nullable = false, length = 250)
    private String contrasenaHashUsuario;

    @Column(nullable = false)
    private LocalDate fechaCreacionPerfil;

    @OneToOne(mappedBy = "credencialesAcceso")
    private Usuario usuario;

    public CredencialesAcceso() {
    }

    public CredencialesAcceso(Integer idUsuario, String emailUsuario, String contrasenaHashUsuario, LocalDate fechaCreacionPerfil) {
        this.idUsuario = idUsuario;
        this.emailUsuario = emailUsuario;
        this.contrasenaHashUsuario = contrasenaHashUsuario;
        this.fechaCreacionPerfil = fechaCreacionPerfil;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getContrasenaHashUsuario() {
        return contrasenaHashUsuario;
    }

    public void setContrasenaHashUsuario(String contrasenaHashUsuario) {
        this.contrasenaHashUsuario = contrasenaHashUsuario;
    }

    public LocalDate getFechaCreacionPerfil() {
        return fechaCreacionPerfil;
    }

    public void setFechaCreacionPerfil(LocalDate fechaCreacionPerfil) {
        this.fechaCreacionPerfil = fechaCreacionPerfil;
    }

    @Override
    public String toString() {
        return "CredencialesAcceso{" +
                "idUsuario=" + idUsuario +
                ", emailUsuario='" + emailUsuario + '\'' +
                ", contrasenaHashUsuario='" + contrasenaHashUsuario + '\'' +
                ", fechaCreacionPerfil=" + fechaCreacionPerfil +
                '}';
    }
}
