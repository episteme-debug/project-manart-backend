package com.example.demo.DTOs.FiltrosProductoDTO;

import com.example.demo.Entidades.ArchivoMultimedia;
import com.example.demo.Entidades.CategoriaProducto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespuestaFiltro {
    private Long idProducto;
    private String nombreProducto;
    private String descripcionProducto;
    private Integer stockProducto;
    private BigDecimal precioProducto;
    private Long idUsuario;
    private List<ArchivoMultimedia> listaArchivos;
    private List<CategoriaProducto> categorias;
}