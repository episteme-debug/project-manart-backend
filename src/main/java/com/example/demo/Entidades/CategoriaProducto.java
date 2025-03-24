package com.example.demo.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CategoriaProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;

    @Column(nullable = false, length = 100)
    private String nombreCategoria;

    @Column(nullable = false, length = 250)
    private String descripcionCategoria;

    @Column(nullable = false)
    private Boolean estadoCategoria;

    @Column(nullable = true)
    private String imagenCategoria;

    @JsonIgnore
    @OneToMany(mappedBy = "categoriaProducto")
    private List<RelacionCategoriaProducto> productosXCategoria;


}