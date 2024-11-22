/*package com.example.demo.Entidades;


import java.util.List;

@Entity
public class Categoria_Publicacion {

    @Column(nullable = false)
    @Id
    private Integer IdCatPublicacion;

    @Column(nullable = false.length = 50)
    private String Nombre_Categoria;

    @Column(nullable = false.length = 50)
    private String DescripcionCat;

    @ManytoMany(mappedBy = "Categoria_Publicacion", cascade = CascadeType.ALL)
    private List<Publicacion> publicacionesList;

    public Categoria_Publicacion() {
    }

    public Categoria_Publicacion(Integer idCatPublicacion, String nombre_Categoria, String descripcionCat, List<Publicacion> publicacionesList) {
        IdCatPublicacion = idCatPublicacion;
        Nombre_Categoria = nombre_Categoria;
        DescripcionCat = descripcionCat;
        this.publicacionesList = publicacionesList;

    }

    public Integer getIdCatPublicacion() {
        return IdCatPublicacion;
    }

    public void setIdCatPublicacion(Integer idCatPublicacion) {
        IdCatPublicacion = idCatPublicacion;
    }

    public String getNombre_Categoria() {
        return Nombre_Categoria;
    }

    public void setNombre_Categoria(String nombre_Categoria) {
        Nombre_Categoria = nombre_Categoria;
    }

    public String getDescripcionCat() {
        return DescripcionCat;
    }

    public void setDescripcionCat(String descripcionCat) {
        DescripcionCat = descripcionCat;
    }

    public List<Publicacion> getPublicacionesList() {
        return publicacionesList;
    }

    public void setPublicacionesList(List<Publicacion> publicacionesList) {
        this.publicacionesList = publicacionesList;
    }
}*/
