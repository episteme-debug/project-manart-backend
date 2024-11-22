package com.example.demo.Entidades;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPublicacion;

    @Column(nullable = false, length = 100)
    private String nombrePublicacion;

    @Column(nullable = false, length = 200)
    private String detallesPublicacion;

    @Column(nullable = false)
    private LocalDate fechaPublicacion;

    @Column(nullable = false)
    private String imagenPublicacion;

    @Column(nullable = false)
    private boolean estadoPublicacion;

    @ManyToOne(targetEntity = Usuario.class)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    //   @ManyToMany(targetEntity = Categoria_Publicacion.class)
////   @JoinColumn(name = "IdCatPublicacion", nullable = false)
////   private Categoria_Publicacion categoria_publicacion;

    public Publicacion(){}

    public Publicacion(Integer idPublicacion, String nombrePublicacion, String detallesPublicacion, LocalDate fechaPublicacion, String imagenPublicacion, boolean estadoPublicacion, Usuario usuario) {
        this.idPublicacion = idPublicacion;
        this.nombrePublicacion = nombrePublicacion;
        this.detallesPublicacion = detallesPublicacion;
        this.fechaPublicacion = fechaPublicacion;
        this.imagenPublicacion = imagenPublicacion;
        this.estadoPublicacion = estadoPublicacion;
        this.usuario = usuario;
    }

    public Integer getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(Integer idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public String getNombrePublicacion() {
        return nombrePublicacion;
    }

    public void setNombrePublicacion(String nombrePublicacion) {
        this.nombrePublicacion = nombrePublicacion;
    }

    public String getDetallesPublicacion() {
        return detallesPublicacion;
    }

    public void setDetallesPublicacion(String detallesPublicacion) {
        this.detallesPublicacion = detallesPublicacion;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getImagenPublicacion() {
        return imagenPublicacion;
    }

    public void setImagenPublicacion(String imagenPublicacion) {
        this.imagenPublicacion = imagenPublicacion;
    }

    public boolean isEstadoPublicacion() {
        return estadoPublicacion;
    }

    public void setEstadoPublicacion(boolean estadoPublicacion) {
        this.estadoPublicacion = estadoPublicacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Publicacion{" +
                "idPublicacion=" + idPublicacion +
                ", nombrePublicacion='" + nombrePublicacion + '\'' +
                ", detallesPublicacion='" + detallesPublicacion + '\'' +
                ", fechaPublicacion=" + fechaPublicacion +
                ", imagenPublicacion='" + imagenPublicacion + '\'' +
                ", estadoPublicacion=" + estadoPublicacion +
                ", usuario=" + usuario +
                '}';
    }
}
