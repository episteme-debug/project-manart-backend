package com.example.demo.DTOs.ProductoDTO;

import com.example.demo.Enums.RegionesDeColombiaEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActualizacionProducto {

    private Long idProducto;
    private String nombreProducto;
    private String descripcionProducto;
    private RegionesDeColombiaEnum regionProducto;
    private Integer stockProducto;
    private BigDecimal precioProducto;
    private String imagenProducto;
    private Boolean estadoProducto;
    private String categoriaProducto;

}
