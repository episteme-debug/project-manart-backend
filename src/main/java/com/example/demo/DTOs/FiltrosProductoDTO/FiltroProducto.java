package com.example.demo.DTOs.FiltrosProductoDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FiltroProducto {
    public String nombreCategoria;
    public Integer porcentajeDescuento;
    public Double precioMin;
    public Double precioMax;
}
