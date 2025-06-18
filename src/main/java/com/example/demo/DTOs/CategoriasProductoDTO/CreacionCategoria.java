package com.example.demo.DTOs.CategoriasProductoDTO;

import com.example.demo.Entidades.ArchivoMultimedia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreacionCategoria {
    private String nombreCategoria;
    private String descripcionCategoria;
}
