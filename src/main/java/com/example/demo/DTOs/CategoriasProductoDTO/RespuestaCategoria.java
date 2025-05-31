package com.example.demo.DTOs.CategoriasProductoDTO;

import com.example.demo.Entidades.ArchivoMultimedia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaCategoria {
    private Long idCategoria;
    private String nombreCategoria;
    private String descripcionCategoria;
    private Boolean estadoCategoria;
    private List<ArchivoMultimedia> archivoMultimedia;
}
