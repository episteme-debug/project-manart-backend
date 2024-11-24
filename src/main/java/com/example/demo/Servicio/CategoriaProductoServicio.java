package com.example.demo.Servicio;

import com.example.demo.Entidades.CategoriaProducto;
import com.example.demo.Repositorios.CategoriaProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CategoriaProductoServicio {

    @Autowired
    CategoriaProductoRepositorio categoriaProductoRepositorio;

    //Get All Categorias Producto
    public List<CategoriaProducto> get_all_categorias_producto(){
        return categoriaProductoRepositorio.findAll();
    }

    //Insert a Categoria Producto
    public CategoriaProducto insert_categoria_producto(CategoriaProducto categoriaProducto){
        return categoriaProductoRepositorio.save(categoriaProducto);
    }
}
