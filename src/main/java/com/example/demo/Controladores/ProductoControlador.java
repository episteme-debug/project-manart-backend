package com.example.demo.Controladores;

import com.example.demo.DTOs.FiltrosProductoDTO.FiltroProducto;
import com.example.demo.DTOs.FiltrosProductoDTO.RangoDePrecios;
import com.example.demo.DTOs.FiltrosProductoDTO.RespuestaFiltro;
import com.example.demo.DTOs.ProductoDTO.CreacionProducto;
import com.example.demo.DTOs.ProductoDTO.RespuestaProducto;
import com.example.demo.DTOs.ProductoDTO.ActualizacionProducto;
import com.example.demo.Enums.RegionesDeColombiaEnum;
import com.example.demo.Servicios.ProductoServicio;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/producto")
@CrossOrigin("http://127.0.0.1:5500/")
public class ProductoControlador {

    @Autowired
    ProductoServicio productoServicio;

    //. Crear nuevo producto
    @PostMapping("private/crear")
    public ResponseEntity<?> crearProducto(@RequestBody CreacionProducto productoDTO) {
        try {
            RespuestaProducto nuevo = productoServicio.crearProducto(productoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);

        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    //. Obtener Producto por Id
    @GetMapping("public/obtenerporid/{idProducto}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long idProducto) {
        try {
            RespuestaProducto producto = productoServicio.obtenerProductoPorId(idProducto);
            return ResponseEntity.ok(producto);

        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        }
    }

    //. Obtener todos los productos
    @GetMapping("public/listarproductos")
    public List<RespuestaProducto> listarProductos() {
        return productoServicio.listarProductos();
    }

    //. Obtener todos los productos
    @GetMapping("public/listarproductosporcategoria/{idCategoria}")
    public List<RespuestaProducto> listarProductosPorCategoria(@PathVariable Long idCategoria) {
        return productoServicio.listarProductosPorCategoria(idCategoria);
    }

    //. Listar productos por region
    @GetMapping("public/listarporregion/{region}")
    public List<RespuestaProducto> listarProductosPorRegion(@PathVariable RegionesDeColombiaEnum region) {
        return productoServicio.listarPorRegion(region);
    }

    //. Listar productos por region
    @GetMapping("private/listarporusuario")
    @PreAuthorize("hasRole('ADMIN') or hasRole('VENDEDOR')")
    public List<RespuestaProducto> listarPorusuario() {
        return productoServicio.listarPorusuario();
    }


    //. Obtener lista de productos por nombre
    @GetMapping("public/obtenerpornombre/{nombreProducto}")
    public ResponseEntity<?> obtenerPorNombre(@PathVariable String nombre) {
        try {
            List<RespuestaProducto> resultados = productoServicio.obtenerProductosPorNombre(nombre);
            return ResponseEntity.ok(resultados);

        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }

    @PostMapping("private/crear-actualizar")
    public ResponseEntity<?> crearOActualizarProducto(@RequestBody CreacionProducto dto) throws Exception {
        if (dto.getIdProducto() == 0) {
            return ResponseEntity.ok(productoServicio.crearProducto(dto));
        } else {
            return ResponseEntity.ok(productoServicio.actualizarProducto(dto));
        }
    }


    //. Actualizar un producto existente
    @PatchMapping("private/actualizarproducto/{idProducto}")
    @PreAuthorize("@autorizacion.esPropietarioProducto(#idProducto) or hasRole('ADMIN')")
    public ResponseEntity<?> actualizarProducto(@PathVariable Long idProducto, @RequestBody CreacionProducto dto) {
        try {
            RespuestaProducto actualizado = productoServicio.actualizarProducto(dto);
            return ResponseEntity.ok(actualizado);

        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //.  Eliminar un producto
    @DeleteMapping("private/eliminar/{idProducto}")
    @PreAuthorize("@autorizacion.esPropietarioProducto(#idProducto) or hasRole('ADMIN')")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long idProducto) {
        try {
            productoServicio.eliminarProducto(idProducto);
            return ResponseEntity.ok().build();

        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }

    @PatchMapping("private/categorizarproducto/{idProducto}")
    @PreAuthorize("@autorizacion.esPropietarioProducto(#idProducto) or hasRole('ADMIN')")
    public ResponseEntity<?> categorizarProducto (@PathVariable Long idProducto, @RequestBody List<Long> idsCategorias) {
        try {
            productoServicio.categorizarProducto(idProducto, idsCategorias);
            return ResponseEntity.ok().body("Categorización exitosa");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("public/filtrar")
    public List<RespuestaFiltro> buscarProductosFiltrados(FiltroProducto filtro) {
        return productoServicio.buscarProductosFiltrados(filtro);
    }

    @GetMapping("public/relacionados/{idProducto}")
    public ResponseEntity<List<RespuestaProducto>> obtenerRelacionados(@PathVariable Long idProducto) {
        List<RespuestaProducto> relacionados = productoServicio.obtenerProductosRelacionados(idProducto);
        return ResponseEntity.ok(relacionados);
    }

    @GetMapping("public/rango-precios")
    public ResponseEntity<RangoDePrecios> obtenerRangoPrecios() {
        RangoDePrecios rango = productoServicio.obtenerRangoDePrecios();
        return (rango != null) ? ResponseEntity.ok(rango) : ResponseEntity.noContent().build();
    }

}

