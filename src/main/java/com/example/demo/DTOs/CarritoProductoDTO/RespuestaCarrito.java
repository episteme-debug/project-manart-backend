package com.example.demo.DTOs.CarritoProductoDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RespuestaCarrito {
    private Long idCarrito;
    private Long idItem;
    private Long idProducto;
    private String nombreProducto;
    private Integer cantidad;
    private BigDecimal precioUnitario = BigDecimal.valueOf(0.00);
    private BigDecimal subtotal = BigDecimal.valueOf(0.00);
}
