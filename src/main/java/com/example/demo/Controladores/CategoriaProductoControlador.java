package com.example.demo.Controladores;

import com.example.demo.Servicios.CategoriaProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://127.0.0.1:5500/")
@RestController
public class CategoriaProductoControlador {

    @Autowired
    CategoriaProductoServicio categoriaProductoServicio;

    @GetMapping("/get_categoria_producto_activa")
    public List<com.example.demo.Entidades.CategoriaProducto> get_all_categorias_producto(){
        return categoriaProductoServicio.get_all_categorias_producto();
    }

    @PostMapping("/save_categoria_producto")
    public void save_categoria_producto(@RequestBody com.example.demo.Entidades.CategoriaProducto categoriaProducto){
        categoriaProductoServicio.insert_categoria_producto(categoriaProducto);
    }

/*    @PostMapping("/saveCategoria")
    public ResponseEntity<CategoriaProducto> saveCategoria(@RequestBody CategoriaProducto categoriaProducto) {
        CategoriaProducto nuevaCategoria = CategoriaProductoServicio.saveCategoria(categoriaProducto);
        return ResponseEntity.ok(nuevaCategoria); // Retorna la categoría guardada como JSON
    }*/
}
