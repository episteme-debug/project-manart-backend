package com.example.demo.Entidades;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDireccion;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private String municipio;

    @Column(nullable = false)
    private String departamento;

    @Column(nullable = false)
    private String codigoPostal;

    @Column(nullable = false)
    private LocalDate fechaRegistro;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    public Direccion() {
    }

    public Direccion(Integer idDireccion, String direccion, String municipio, String departamento, String codigoPostal, LocalDate fechaRegistro, Usuario usuario) {
        this.idDireccion = idDireccion;
        this.direccion = direccion;
        this.municipio = municipio;
        this.departamento = departamento;
        this.codigoPostal = codigoPostal;
        this.fechaRegistro = fechaRegistro;
        this.usuario = usuario;
    }

    public Integer getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Integer idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Direccion{" +
                "idDireccion=" + idDireccion +
                ", direccion='" + direccion + '\'' +
                ", municipio='" + municipio + '\'' +
                ", departamento='" + departamento + '\'' +
                ", codigoPostal='" + codigoPostal + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", usuario=" + usuario +
                '}';
    }
}
