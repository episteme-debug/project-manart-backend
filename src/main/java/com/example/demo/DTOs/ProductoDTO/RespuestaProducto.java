package com.example.demo.DTOs.ProductoDTO;

import com.example.demo.Entidades.ArchivoMultimedia;
import com.example.demo.Enums.RegionesDeColombiaEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespuestaProducto {
    private Long idProducto;
    private String nombreProducto;
    private String descripcionProducto;
    private RegionesDeColombiaEnum regionProducto;
    private Integer stockProducto;
    private BigDecimal precioProducto;
    private Long idUsuario;
    private List<ArchivoMultimedia> listaArchivos;
    private List<String> listaCategorias;

}
