package com.example.demo.DTOs.FiltrosProductoDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FiltroProducto {
    public String nombreCategoria;
    public Integer porcentajeDescuento;
    public BigDecimal precioMin;
    public BigDecimal precioMax;
}
