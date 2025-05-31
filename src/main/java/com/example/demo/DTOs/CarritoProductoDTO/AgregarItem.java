package com.example.demo.DTOs.CarritoProductoDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgregarItem {
    private Long idProducto;
    private Integer cantidad;
}
