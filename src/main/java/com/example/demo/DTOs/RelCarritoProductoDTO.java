package com.example.demo.DTOs;

import com.example.demo.Entidades.CarritoCompra;
import com.example.demo.Entidades.Producto;
import com.example.demo.Entidades.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelCarritoProductoDTO {
    private Usuario usuario;
    private Producto producto;
    private Integer cantidad = 0;
    private BigDecimal precioUnitario = BigDecimal.valueOf(0.00);
    private BigDecimal subtotal = BigDecimal.valueOf(0.00);
}
