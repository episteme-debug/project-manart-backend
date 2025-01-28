package com.example.demo.Servicio;

import com.example.demo.Entidades.Producto;
import com.example.demo.Repositorios.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductoServicio {

    @Autowired
    ProductoRepositorio ProductoRepositorio;

    //Obtener producto por Id
    public Optional<Producto> getProductoById(Integer id){
        return ProductoRepositorio.findById(id);
    }

    //Obtener todos los productos
    public List<Producto> get_all_productos(){
        return ProductoRepositorio.findAll();
    }
}
