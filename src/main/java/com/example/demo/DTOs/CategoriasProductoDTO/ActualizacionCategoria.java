package com.example.demo.DTOs.CategoriasProductoDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActualizacionCategoria {

    private Long idCategoria;
    private String nombreCategoria;
    private String descripcionCategoria;
    private Boolean estadoCategoria;
    private String imagenCategoria;
}
