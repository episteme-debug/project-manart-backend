package com.example.demo.controlador;

import com.example.demo.Entidades.Producto;
import com.example.demo.Servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;
@RestController
@CrossOrigin("http://127.0.0.1:5500/")
public class ProductoControlador {

        @Autowired
        ProductoServicio ProductoServicio;

        @GetMapping("/getProductoById/{id}")
        public Optional<Producto> getProducto(@PathVariable Integer id) {
            return ProductoServicio.getProductoById(id);
        }
    }
