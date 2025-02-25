package com.example.demo.Controladores;

import com.example.demo.Entidades.CategoriaProducto;
import com.example.demo.Repositorios.CategoriaProductoRepositorio;
import com.example.demo.Servicios.CategoriaProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://127.0.0.1:5501/")
public class CategoriaProductoControladorFormData {

    @Autowired
    CategoriaProductoServicio CategoriaProductoServicio;

    @Autowired
    CategoriaProductoRepositorio CategoriaProductoRepositorio;

    @GetMapping( "/GetallCategoria" )
    public List<CategoriaProducto>GetallCategoria(){
        return CategoriaProductoServicio.get_all_categorias_producto();
    }
    @PostMapping(value ="/saveCategoria",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveCategoria(
           @RequestParam("nombreCategoria") String nombreCategoriav,
           @RequestParam("descripcionCategoria") String descripcionCategoria,
           @RequestParam("estadoCategoria") boolean estadoCategoria,
           @RequestParam("imagenCategoria") MultipartFile imagenCategoria,
           @RequestParam("ordenVisualizacionCategoria") int ordenVisualizacionCategoria
    ) throws IOException {
        CategoriaProducto categoriaProducto = new CategoriaProducto();
        categoriaProducto.setNombreCategoria(nombreCategoriav);
        categoriaProducto.setDescripcionCategoria(descripcionCategoria);
        categoriaProducto.setEstadoCategoria(estadoCategoria);
        categoriaProducto.setImagenCategoria(imagenCategoria.getBytes());
        categoriaProducto.setOrdenVisualizacionCategoria(ordenVisualizacionCategoria);

        CategoriaProductoRepositorio.save(categoriaProducto);

        return ResponseEntity.ok("se guardo con exito");
     }
    @GetMapping("/GetProducto/{id}")
    public ResponseEntity<byte[]> GetImagen(@PathVariable("id")Integer id){
        Optional<CategoriaProducto> CategoriaProductoOpt = CategoriaProductoRepositorio.findById(id);

        if (!CategoriaProductoOpt.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CategoriaProducto CategoriaProducto = CategoriaProductoOpt.get();
        byte[] imagenBytes = CategoriaProducto.getImagenCategoria();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return  new ResponseEntity<>(imagenBytes, headers, HttpStatus.OK);
    }
}
