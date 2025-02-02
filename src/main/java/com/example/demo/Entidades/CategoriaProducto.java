package com.example.demo.Entidades;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class CategoriaProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategoria;

    @Column(nullable = false, length = 100)
    private String nombreCategoria;

    @Column(nullable = false, length = 250)
    private String descripcionCategoria;

    @Column(nullable = false)
    private boolean estadoCategoria;

    @Lob
    @Column(nullable = false, columnDefinition = "LONGBLOB")
    private byte[] imagenCategoria;

    @Column(nullable = false)
    private int ordenVisualizacionCategoria;

    @ManyToMany
    @JoinTable(
            name = "categoriaxproducto",  // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "idCategoria"),  // FK de la entidad 'Categoria'
            inverseJoinColumns = @JoinColumn(name = "idProducto")  // FK de la entidad 'Producto'
    )
    private List<Producto> productos;

    public CategoriaProducto() {
    }

    public CategoriaProducto(List<Producto> productos, Integer idCategoria, String nombreCategoria, String descripcionCategoria, boolean estadoCategoria, byte[] imagenCategoria, int ordenVisualizacionCategoria) {
        this.productos = productos;
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.descripcionCategoria = descripcionCategoria;
        this.estadoCategoria = estadoCategoria;
        this.imagenCategoria = imagenCategoria;
        this.ordenVisualizacionCategoria = ordenVisualizacionCategoria;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getDescripcionCategoria() {
        return descripcionCategoria;
    }

    public void setDescripcionCategoria(String descripcionCategoria) {
        this.descripcionCategoria = descripcionCategoria;
    }

    public boolean getEstadoCategoria() {
        return estadoCategoria;
    }

    public void setEstadoCategoria(boolean estadoCategoria) {
        this.estadoCategoria = estadoCategoria;
    }

    public byte[] getImagenCategoria() {
        return imagenCategoria;
    }

    public void setImagenCategoria(byte[] imagenCategoria) {
        this.imagenCategoria = imagenCategoria;
    }

    public int getOrdenVisualizacionCategoria() {
        return ordenVisualizacionCategoria;
    }

    public void setOrdenVisualizacionCategoria(int ordenVisualizacionCategoria) {
        this.ordenVisualizacionCategoria = ordenVisualizacionCategoria;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "CategoriaProducto{" +
                "idCategoria=" + idCategoria +
                ", nombreCategoria='" + nombreCategoria + '\'' +
                ", descripcionCategoria='" + descripcionCategoria + '\'' +
                ", estadoCategoria=" + estadoCategoria +
                ", imagenCategoria='" + imagenCategoria + '\'' +
                ", ordenVisualizacionCategoria=" + ordenVisualizacionCategoria +
                '}';
    }
}