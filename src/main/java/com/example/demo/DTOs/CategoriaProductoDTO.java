package com.example.demo.DTOs;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaProductoDTO {

    private Long idCategoria;
    private String nombreCategoria;
    private String descripcionCategoria;
    private Boolean estadoCategoria;
    private String imagenCategoria;
}
