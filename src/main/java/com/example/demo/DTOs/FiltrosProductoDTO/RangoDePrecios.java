package com.example.demo.DTOs.FiltrosProductoDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RangoDePrecios {
    private BigDecimal precioMinimo;
    private BigDecimal precioMaximo;
}
