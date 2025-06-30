package com.example.demo.DTOs.ProductoDTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlitroProductoDTO {
    public String nombreCategoria;
    public Integer porcentajeDescuento;
    public Double precioMin;
    public Double precioMax;
    public String region;
}
