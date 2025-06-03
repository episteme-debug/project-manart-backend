package com.example.demo.Controladores;

import com.example.demo.Entidades.Producto;
import com.example.demo.Servicios.FlitroProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://127.0.0.1:5500/")
@RequestMapping("api/filtro")
@RestController
public class FiltroProductoControlador {

    @Autowired
    FlitroProductoServicio flitroProductoServicio;


}
