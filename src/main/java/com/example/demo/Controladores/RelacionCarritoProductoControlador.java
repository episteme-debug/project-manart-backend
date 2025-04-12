package com.example.demo.Controladores;

import com.example.demo.Entidades.RelacionCarritoProducto;
import com.example.demo.Servicios.RelacionCarritoProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("http://127.0.0.1:5500/")
@RequestMapping("/api/relcarritoproducto")
@RestController
public class RelacionCarritoProductoControlador {

    @Autowired
    RelacionCarritoProductoServicio relacionCarritoProductoServicio;

    //. Agregar producto
    @PostMapping("/agregarproducto")
    public RelacionCarritoProducto agregarProducto(@RequestBody RelacionCarritoProducto relacionCarritoProducto){
        return relacionCarritoProductoServicio.crearProducto(relacionCarritoProducto);
    }

    //Get todoas los productos
    @GetMapping("/listarproductos")
    public List<RelacionCarritoProducto> listarProductos(){
        return relacionCarritoProductoServicio.listarProductos();
    }

    //actulizar cantidad
    @PutMapping("/actualizarcantidad/{id}/{cantidad}")
    public void actualizarCantidad(@PathVariable Long id, @PathVariable Integer cantidad)
    {
        relacionCarritoProductoServicio.actualizarCantidad(id, cantidad);
    }


    //eliminar producto
    @DeleteMapping("/eliminarproducto/{id}")
    public void eliminarProducto(@PathVariable Long id){
        relacionCarritoProductoServicio.eliminarProducto(id);
    }
}
