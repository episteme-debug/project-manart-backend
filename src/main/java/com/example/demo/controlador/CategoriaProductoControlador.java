package com.example.demo.controlador;

import com.example.demo.Entidades.CategoriaProducto;
import com.example.demo.Servicio.CategoriaProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoriaProductoControlador {

    @Autowired
    CategoriaProductoServicio categoriaProductoServicio;

    @GetMapping("/get_all_categoiras_producto")
    public void get_all_categorias_producto(){
        categoriaProductoServicio.get_all_categorias_producto();
    }

    @PostMapping("/save_categoria_producto")
    public void save_categoria_producto(@RequestBody CategoriaProducto categoriaProducto){
        categoriaProductoServicio.insert_categoria_producto(categoriaProducto);
    }
}
