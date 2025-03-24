package com.example.demo.Entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RelacionCategoriaProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRelacionCategoriaProducto;

    @ManyToOne(targetEntity = Producto.class)
    @JoinColumn(name = "idProducto", nullable = true)
    private Producto producto;

    @ManyToOne(targetEntity = CategoriaProducto.class)
    @JoinColumn(name = "idCategoriaProducto")
    private CategoriaProducto categoriaProducto;
}
