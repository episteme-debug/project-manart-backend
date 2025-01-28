package com.example.demo.Entidades;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto;

    @Column(nullable = false, length = 100)
    private String nombreProducto;

    @Column(nullable = false, length = 500)
    private String descripcionProducto;

    @Column(nullable = false, length = 1000)
    private Integer stockProducto;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioProducto;

    @Column(nullable = false)
    private String imagenProducto;

    @ManyToMany(mappedBy = "productos")
    private List<CategoriaProducto> categorias;

    @ManyToOne(targetEntity = Artesano.class)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Artesano artesano;

    @ManyToOne(targetEntity = Promocion.class)
    @JoinColumn(name = "idPromocion", nullable = false)
    private Promocion promocion;

    @OneToMany(mappedBy = "producto")
    private List<CarritoCompra> carritoCompras;

/*    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<Valoracion> valoraciones;*/

    public Producto() {
    }

    public Producto(Integer idProducto, String nombreProducto, String descripcionProducto, Integer stockProducto, BigDecimal precioProducto, String imagenProducto) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.stockProducto = stockProducto;
        this.precioProducto = precioProducto;
        this.imagenProducto = imagenProducto;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public Integer getStockProducto() {
        return stockProducto;
    }

    public void setStockProducto(Integer stockProducto) {
        this.stockProducto = stockProducto;
    }

    public BigDecimal getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(BigDecimal precioProducto) {
        this.precioProducto = precioProducto;
    }

    public String getImagenProducto() {
        return imagenProducto;
    }

    public void setImagenProducto(String imagenProducto) {
        this.imagenProducto = imagenProducto;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", descripcionProducto='" + descripcionProducto + '\'' +
                ", stockProducto=" + stockProducto +
                ", precioProducto=" + precioProducto +
                ", imagenProducto='" + imagenProducto + '\'' +
                '}';
    }
}