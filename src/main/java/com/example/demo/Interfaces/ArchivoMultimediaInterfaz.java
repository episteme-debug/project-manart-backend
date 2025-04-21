package com.example.demo.Interfaces;

import com.example.demo.Enums.TipoArchivoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public interface ArchivoMultimediaInterfaz {
    Long getId();
    LocalDate getFechaCreacion();
    String getNombre();
    String getRuta();
    TipoArchivoEnum getTipo();
    Long getUsuarioIdUsuario();
    Long getProductoIdProducto();
    Long getCategoriaProductoIdCategoria();
    Long getPublicacionId();
}
