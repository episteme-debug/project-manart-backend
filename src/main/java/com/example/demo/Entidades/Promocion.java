package com.example.demo.Entidades;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Promocion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPromocion;

    @Column(nullable = false, length = 100)
    private String nombrePromocion;

    @Column(nullable = false, length = 200)
    private String detallesPromocion;

    @Column(nullable = false)
    private LocalDate fechaInicioPromocion;

    @Column(nullable = false)
    private LocalDate fechaFinPromocion;

    @Column(nullable = false, length=50)
    private Integer porcentajeDescuentoPromocion;

    @Column(nullable = false)
    private Boolean estadoPromocion;

    @OneToMany(mappedBy = "promocion", cascade = CascadeType.ALL)
    List<Producto> producto;

    public Promocion() {
    }

    public Promocion(Integer idPromocion, String nombrePromocion, String detallesPromocion, LocalDate fechaInicioPromocion, LocalDate fechaFinPromocion, List<Producto> producto, Integer porcentajeDescuentoPromocion, Boolean estadoPromocion) {
        this.idPromocion = idPromocion;
        this.nombrePromocion = nombrePromocion;
        this.detallesPromocion = detallesPromocion;
        this.fechaInicioPromocion = fechaInicioPromocion;
        this.fechaFinPromocion = fechaFinPromocion;
        this.producto = producto;
        this.porcentajeDescuentoPromocion = porcentajeDescuentoPromocion;
        this.estadoPromocion = estadoPromocion;
    }

    public Integer getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(Integer idPromocion) {
        this.idPromocion = idPromocion;
    }

    public String getNombrePromocion() {
        return nombrePromocion;
    }

    public void setNombrePromocion(String nombrePromocion) {
        this.nombrePromocion = nombrePromocion;
    }

    public String getDetallesPromocion() {
        return detallesPromocion;
    }

    public void setDetallesPromocion(String detallesPromocion) {
        this.detallesPromocion = detallesPromocion;
    }

    public LocalDate getFechaInicioPromocion() {
        return fechaInicioPromocion;
    }

    public void setFechaInicioPromocion(LocalDate fechaInicioPromocion) {
        this.fechaInicioPromocion = fechaInicioPromocion;
    }

    public LocalDate getFechaFinPromocion() {
        return fechaFinPromocion;
    }

    public void setFechaFinPromocion(LocalDate fechaFinPromocion) {
        this.fechaFinPromocion = fechaFinPromocion;
    }

    public List<Producto> getProducto() {
        return producto;
    }

    public void setProducto(List<Producto> producto) {
        this.producto = producto;
    }

    public Integer getPorcentajeDescuentoPromocion() {
        return porcentajeDescuentoPromocion;
    }

    public void setPorcentajeDescuentoPromocion(Integer porcentajeDescuentoPromocion) {
        this.porcentajeDescuentoPromocion = porcentajeDescuentoPromocion;
    }

    public Boolean getEstadoPromocion() {
        return estadoPromocion;
    }

    public void setEstadoPromocion(Boolean estadoPromocion) {
        this.estadoPromocion = estadoPromocion;
    }

    @Override
    public String toString() {
        return "Promocion{" +
                "idPromocion=" + idPromocion +
                ", nombrePromocion='" + nombrePromocion + '\'' +
                ", detallesPromocion='" + detallesPromocion + '\'' +
                ", fechaInicioPromocion=" + fechaInicioPromocion +
                ", fechaFinPromocion=" + fechaFinPromocion +
                ", producto=" + producto +
                ", porcentajeDescuentoPromocion=" + porcentajeDescuentoPromocion +
                ", estadoPromocion=" + estadoPromocion +
                '}';
    }
}
