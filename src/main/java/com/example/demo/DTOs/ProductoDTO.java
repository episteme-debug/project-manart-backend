package com.example.demo.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {

    private Long idProducto;
    private String nombreProducto;
    private String descripcionProducto;
    private Integer stockProducto;
    private BigDecimal precioProducto;
    private String imagenProducto;
    private Boolean estadoProducto;
    private String categoriaProducto;

}
