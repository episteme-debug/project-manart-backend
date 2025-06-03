package com.example.demo.Servicios;

import com.example.demo.Entidades.Producto;
import com.example.demo.Repositorios.FlitroProductosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlitroProductoServicio {

    @Autowired
    FlitroProductosRepositorio flitroProductosRepositorio;




}
