package com.example.demo.Servicio;

import com.example.demo.Entidades.CategoriaProducto;
import com.example.demo.Repositorios.CategoriaProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaProductoServicio {

    @Autowired
    private CategoriaProductoRepositorio categoriaProductoRepositorio;

    // Guardar una categoría en la base de datos
    public CategoriaProducto saveCategoria(CategoriaProducto categoriaProducto) {
        return categoriaProductoRepositorio.save(categoriaProducto);
    }
}
