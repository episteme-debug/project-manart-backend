package com.example.demo.DTOs;

import com.example.demo.Entidades.CategoriaProducto;
import com.example.demo.Entidades.Producto;
import com.example.demo.Entidades.Publicacion;
import com.example.demo.Entidades.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArchivoMultimediaDTO {
    private Usuario usuario;
    private Producto producto;
    private Publicacion publicacion;
    private CategoriaProducto categoriaProducto;
}
