package com.example.demo.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    @Column(nullable = false, length = 100)
    private String nombreProducto;

    @Column(nullable = false, length = 500)
    private String descripcionProducto;

    @Column(nullable = false)
    private Integer stockProducto;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioProducto;

    @Column(nullable = false)
    private String imagenProducto;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean estadoProducto;

    @ManyToOne(targetEntity = Usuario.class)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(targetEntity = Promocion.class)
    @JoinColumn(name = "idPromocion", nullable = true)
    private Promocion promocion;

    @OneToMany(mappedBy = "producto")
    @JsonIgnore
    private List<RelacionCategoriaProducto> categoriasXProducto;

    @OneToMany(mappedBy = "producto")
    private List<CarritoCompra> carritoCompras;

/*    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<Valoracion> valoraciones;*/


}