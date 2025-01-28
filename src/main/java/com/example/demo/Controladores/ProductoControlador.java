package com.example.demo.Controladores;

import com.example.demo.Entidades.Producto;
import com.example.demo.Servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://127.0.0.1:5500/")
public class ProductoControlador {

    @Autowired
    ProductoServicio ProductoServicio;

    //Obtener Producto por Id
    @GetMapping("/getProductoById/{id}")
    public Optional<Producto> getProducto(@PathVariable Integer id) {
        return ProductoServicio.getProductoById(id);
    }

    //Obtener todos los productos
    @GetMapping("/getAllProductos")
    public List<Producto> get_all_productos() {
        return ProductoServicio.get_all_productos();
    }
}
