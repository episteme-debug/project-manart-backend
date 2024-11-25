package com.example.demo.controlador;

import com.example.demo.Entidades.CategoriaProducto;
import com.example.demo.Repositorios.CategoriaProductoRepositorio;
import com.example.demo.Servicio.CategoriaProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://127.0.0.1:5500/")
public class CategoriaProductControlador {

    @Autowired
    CategoriaProductoServicio CategoriaProductoServicio;

    @Autowired
    CategoriaProductoRepositorio CategoriaProductoRepositorio;

    @PostMapping("/saveCategoria")
    public ResponseEntity<CategoriaProducto> saveCategoria(@RequestBody CategoriaProducto categoriaProducto) {
        CategoriaProducto nuevaCategoria = CategoriaProductoServicio.saveCategoria(categoriaProducto);
        return ResponseEntity.ok(nuevaCategoria); // Retorna la categoría guardada como JSON
    }
}
