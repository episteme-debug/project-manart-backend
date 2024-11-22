package com.example.demo.Entidades;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEvento;

    @Column(nullable = false, length = 50)
    private String nombreEvento;

    @Column(nullable = false, length = 200)
    private String descripcionEvento;

    @Column(nullable = false, length = 50)
    private LocalDate fechaEvento;

    @Column(nullable = false, length = 50)
    private String ubicacionEvento;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Organizador organizador;

    @Column(nullable = false, length = 50)
    private String tipoEvento;

    @Column(nullable = false, length = 50)
    private boolean estadoEvento;

    public Evento() {
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getDescripcionEvento() {
        return descripcionEvento;
    }

    public void setDescripcionEvento(String descripcionEvento) {
        this.descripcionEvento = descripcionEvento;
    }

    public LocalDate getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(LocalDate fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public String getUbicacionEvento() {
        return ubicacionEvento;
    }

    public void setUbicacionEvento(String ubicacionEvento) {
        this.ubicacionEvento = ubicacionEvento;
    }

    public Organizador getOrganizador() {
        return organizador;
    }

    public void setOrganizador(Organizador organizador) {
        this.organizador = organizador;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public boolean isEstadoEvento() {
        return estadoEvento;
    }

    public void setEstadoEvento(boolean estadoEvento) {
        this.estadoEvento = estadoEvento;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "idEvento=" + idEvento +
                ", nombreEvento='" + nombreEvento + '\'' +
                ", descripcionEvento='" + descripcionEvento + '\'' +
                ", fechaEvento=" + fechaEvento +
                ", ubicacionEvento='" + ubicacionEvento + '\'' +
                ", organizador=" + organizador +
                ", tipoEvento='" + tipoEvento + '\'' +
                ", estadoEvento=" + estadoEvento +
                '}';
    }
}
