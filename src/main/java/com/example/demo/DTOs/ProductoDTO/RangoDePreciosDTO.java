package com.example.demo.DTOs.ProductoDTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RangoDePreciosDTO {
    private Double precioMinimo;
    private Double precioMaximo;
}
