package com.example.demo.Entidades;

import com.example.demo.Enums.RegionesDeColombiaEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    @Enumerated(EnumType.STRING)
    private RegionesDeColombiaEnum regionProducto;

    @Column(nullable = false)
    private Integer stockProducto;

    @Column(nullable = false, scale = 2)
    private BigDecimal precioProducto;

    @Column(nullable = false)
    private String imagenProducto = "avatarGenerico.jpg";

    @Column(nullable = false)
    private Boolean estadoProducto = true;

    //Relaciones

    @ManyToOne(targetEntity = Usuario.class)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(targetEntity = Promocion.class)
    @JoinColumn(name = "idPromocion", nullable = true)
    private Promocion promocion;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RelacionCarritoProducto> relacionCarritoProductos;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReseñaProducto> reseñaProducto;

    @ManyToMany
    @JoinTable(
            name = "relacionProductoCategoria",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private List<CategoriaProducto> categorias = new ArrayList<>();

}