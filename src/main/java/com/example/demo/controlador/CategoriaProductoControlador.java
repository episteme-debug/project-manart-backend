package com.example.demo.controlador;

import com.example.demo.Entidades.CategoriaProducto;
import com.example.demo.Servicio.CategoriaProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://127.0.0.1:5501/")
@RestController
public class CategoriaProductoControlador {

    @Autowired
    CategoriaProductoServicio categoriaProductoServicio;

    @GetMapping("/get_categoria_producto_activa")
    public List<CategoriaProducto> get_all_categorias_producto(){
        return categoriaProductoServicio.get_all_categorias_producto();
    }

    @PostMapping("/save_categoria_producto")
    public void save_categoria_producto(@RequestBody CategoriaProducto categoriaProducto){
        categoriaProductoServicio.insert_categoria_producto(categoriaProducto);
    }

/*    @PostMapping("/saveCategoria")
    public ResponseEntity<CategoriaProducto> saveCategoria(@RequestBody CategoriaProducto categoriaProducto) {
        CategoriaProducto nuevaCategoria = CategoriaProductoServicio.saveCategoria(categoriaProducto);
        return ResponseEntity.ok(nuevaCategoria); // Retorna la categoría guardada como JSON
    }*/
}
