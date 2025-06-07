package com.example.demo.DTOs.ProductoDTO;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RangoDePreciosDTO {
    private BigDecimal precioMinimo;
    private BigDecimal precioMaximo;
}
