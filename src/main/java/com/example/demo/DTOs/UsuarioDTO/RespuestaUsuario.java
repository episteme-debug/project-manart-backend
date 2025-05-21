package com.example.demo.DTOs.UsuarioDTO;

import com.example.demo.DTOs.ProductoDTO.RespuestaProducto;
import com.example.demo.Entidades.ArchivoMultimedia;
import com.example.demo.Enums.UsuarioEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespuestaUsuario {
    private Long idUsuario;
    private String alias;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String emailUsuario;
    private String telefonoUsuario;
    private Boolean estadoUsuario;
    private UsuarioEnum rolUsuario;
    private List<ArchivoMultimedia> listaArchivos;
    private List<RespuestaProducto> listaProductos;
}
