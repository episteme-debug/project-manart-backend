package com.example.demo.Servicios;

import com.example.demo.DTOs.FiltrosProductoDTO.FiltroProducto;
import com.example.demo.DTOs.FiltrosProductoDTO.RangoDePrecios;
import com.example.demo.DTOs.FiltrosProductoDTO.RespuestaFiltro;
import com.example.demo.DTOs.ProductoDTO.CreacionProducto;
import com.example.demo.DTOs.ProductoDTO.RespuestaProducto;
import com.example.demo.DTOs.ProductoDTO.ActualizacionProducto;
import com.example.demo.Entidades.ArchivoMultimedia;
import com.example.demo.Entidades.CategoriaProducto;
import com.example.demo.Entidades.Producto;
import com.example.demo.Entidades.Usuario;
import com.example.demo.Enums.EntidadesArchivoMultimediaEnum;
import com.example.demo.Enums.RegionesDeColombiaEnum;
import com.example.demo.Repositorios.CategoriaProductoRepositorio;
import com.example.demo.Repositorios.ProductoRepositorio;
import com.example.demo.Repositorios.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoServicio {

    private final ProductoRepositorio productoRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final ArchivoMultimediaServicio archivoMultimediaServicio;
    private final CategoriaProductoRepositorio categoriaProductoRepositorio;

    public Long obtenerIdUsuarioAutenticado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            throw new IllegalStateException("No hay un usuario autenticado.");
        }

        Usuario usuarioAutenticado = (Usuario) auth.getPrincipal();
        Long idUsuario = usuarioAutenticado.getIdUsuario();

        return idUsuario;
    }

    //. Crear nuevo producto
    public RespuestaProducto crearProducto(CreacionProducto productoDTO) throws Exception {
        Producto producto = new Producto();
        Usuario usuario = usuarioRepositorio.findById(productoDTO.getIdUsuario())
                        .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        if (productoDTO.getIdProducto() != 0)
            producto.setIdProducto(productoDTO.getIdProducto());

        producto.setNombreProducto(productoDTO.getNombreProducto());
        producto.setDescripcionProducto(productoDTO.getDescripcionProducto());
        producto.setRegionProducto(productoDTO.getRegionProducto());
        producto.setPrecioProducto(productoDTO.getPrecioProducto());
        producto.setStockProducto(productoDTO.getStockProducto());
        producto.setUsuario(usuario);

        Producto nuevoProducto = productoRepositorio.save(producto);
        nuevoProducto.setCategorias(categorizarProducto(nuevoProducto.getIdProducto(), productoDTO.getListaCategorias()));

        return generarRespuesta(nuevoProducto);
    }

    //. Obtener producto por Id
    public RespuestaProducto obtenerProductoPorId(Long id) throws BadRequestException {
        if (id == null || id <= 0) {
            throw new BadRequestException("ID inválido.");
        }

        Producto producto = productoRepositorio.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Producto con ID " + id + " no existe."));

        return generarRespuesta(producto);
    }

    //. Obtener todos los productos
    public List<RespuestaProducto> listarProductos() {
        List<RespuestaProducto> listadoRespuestaProducto = new ArrayList<>();
        List<Producto> listadoProductos = productoRepositorio.findAll();

        for (int i = 0; i < listadoProductos.size(); i++) {
            Producto producto = listadoProductos.get(i);
            RespuestaProducto respuestaProducto = generarRespuesta(producto);
            listadoRespuestaProducto.add(respuestaProducto);
        }

        return listadoRespuestaProducto;
    }

    //. Listar productos por usuario
    public List<RespuestaProducto> listarPorusuario() {
        Long idusuario = obtenerIdUsuarioAutenticado();

        List<RespuestaProducto> listadoRespuestaProducto = new ArrayList<>();
        List<Producto> listadoProductos = productoRepositorio.findByUsuario_IdUsuario(idusuario);

        for (int i = 0; i < listadoProductos.size(); i++) {
            Producto producto = listadoProductos.get(i);
            RespuestaProducto respuestaProducto = generarRespuesta(producto);
            listadoRespuestaProducto.add(respuestaProducto);
        }

        return listadoRespuestaProducto;
    }

    //. Obtener productos por nombre
    public List<RespuestaProducto> obtenerProductosPorNombre(String nombre) throws BadRequestException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new BadRequestException("El nombre para buscar es obligatorio.");
        }
        List<RespuestaProducto> listadoRespuestaProducto = new ArrayList<>();
        List<Producto> listadoProductos = productoRepositorio.findByNombreProducto(nombre);

        for (int i = 0; i < listadoProductos.size(); i++) {
            Producto producto = listadoProductos.get(i);
            RespuestaProducto respuestaProducto = generarRespuesta(producto);
            listadoRespuestaProducto.add(respuestaProducto);
        }

        return listadoRespuestaProducto;
    }

    // Actualizaar uno o más datos de un producto
    public RespuestaProducto actualizarProducto(CreacionProducto dto) throws Exception {
        Long id = dto.getIdProducto();
        if (id == null || id <= 0 || !productoRepositorio.existsById(id)) {
            throw new BadRequestException("ID inválido");
        }

        Producto producto = productoRepositorio.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Producto no encontrado"));

        if (dto.getNombreProducto() != null) {
            producto.setNombreProducto(dto.getNombreProducto().trim());
        }

        if (dto.getDescripcionProducto() != null) {
            producto.setDescripcionProducto(dto.getDescripcionProducto().trim());
        }

        if (dto.getRegionProducto() != null) {
            producto.setRegionProducto(dto.getRegionProducto());
        }

        if (dto.getPrecioProducto() != null) {
            producto.setPrecioProducto(dto.getPrecioProducto());
        }

        if (dto.getStockProducto() != null) {
            producto.setStockProducto(dto.getStockProducto());
        }

        if(dto.getListaCategorias() != null){
            producto.setCategorias(categorizarProducto(producto.getIdProducto(), dto.getListaCategorias()));
        }

        Producto productoActualizado = productoRepositorio.save(producto);

        return generarRespuesta(productoActualizado);
    }


    //. Actualizar Stock
    public void actualizarStock(Producto producto, int cantidad, boolean esAgregar) {
        if (!esAgregar) {
            cantidad = -cantidad;
        }
        producto.setStockProducto(producto.getStockProducto() + cantidad);
    }

    //. Eliminar producto
    public void eliminarProducto(Long id) throws BadRequestException {
        if (id == null || id <= 0) {
            throw new BadRequestException("ID inválido.");
        }
        if (!productoRepositorio.existsById(id)) {
            throw new NoSuchElementException("Producto con ID " + id + " no existe.");
        }
        productoRepositorio.deleteById(id);
    }

    //. Categorizar productos
    public List<CategoriaProducto> categorizarProducto(Long idProducto, List<Long> idsCategorias) throws Exception {
        Producto producto = productoRepositorio.findById(idProducto)
                .orElseThrow( () -> new NoSuchElementException("El producto no fue encontrado."));
        List<CategoriaProducto> categoriaProductos = producto.getCategorias();

        if (idsCategorias.isEmpty()) {
            throw new IllegalArgumentException("La lista no puede estar vacía.");
        }

        List<CategoriaProducto> listadoCategorias = new ArrayList<>();

        for (Long idCategoria : idsCategorias) {
            CategoriaProducto categoria = categoriaProductoRepositorio.findById(idCategoria)
                    .orElseThrow(() -> new NoSuchElementException("Categoría no encontrada en la base de datos."));
            if (categoriaProductos.contains(categoria))
                continue;

            categoriaProductos.add(categoria);
        }

        productoRepositorio.save(producto);
        return categoriaProductos;
    }

    //. Listado de productos por categoría
    public List<RespuestaProducto> listarProductosPorCategoria(Long idCategoria) {
        CategoriaProducto categoria = categoriaProductoRepositorio.findById(idCategoria)
                .orElseThrow(() -> new NoSuchElementException("No se puede encontrar la categoría."));

        List<Producto> productos = productoRepositorio.findByCategorias_IdCategoria(idCategoria);
        List<RespuestaProducto> respuestaProductoProductos = new ArrayList<>();

        for (Producto producto : productos) {
            RespuestaProducto respuestaProducto = generarRespuesta(producto);
            respuestaProductoProductos.add(respuestaProducto);
        }

        return respuestaProductoProductos;
    }

    public List<RespuestaProducto> listarPorUsuario (Long idUsuario) {
        Usuario usuario = usuarioRepositorio.findById(idUsuario)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        List<Producto> productos = productoRepositorio.findByUsuario_IdUsuario(idUsuario);
        List<RespuestaProducto> productosRespuesta = new ArrayList<>();

        for (Producto producto : productos) {
            RespuestaProducto respuesta = generarRespuesta(producto);
            productosRespuesta.add(respuesta);
        }

        return productosRespuesta;
    }

    public List<RespuestaProducto> listarPorRegion (RegionesDeColombiaEnum region) {
        List<Producto> productos = productoRepositorio.findByRegionProducto(region);
        List<RespuestaProducto> productosRespuesta = new ArrayList<>();

        for (Producto producto : productos) {
            RespuestaProducto respuesta = generarRespuesta(producto);
            productosRespuesta.add(respuesta);
        }

        return productosRespuesta;
    }

    public List<RespuestaFiltro> buscarProductosFiltrados(FiltroProducto dto) {
        List<Producto> productos = productoRepositorio.findByProductosFiltrados(
                dto.getNombreCategoria(),
                dto.getPorcentajeDescuento(),
                dto.getPrecioMin(),
                dto.getPrecioMax()
        );
        List<RespuestaFiltro> respuesta = productos.stream().map(p->{
            RespuestaFiltro rp = new RespuestaFiltro();
            rp.setIdProducto(p.getIdProducto());
            rp.setNombreProducto(p.getNombreProducto());
            rp.setDescripcionProducto(p.getDescripcionProducto());
            rp.setStockProducto(p.getStockProducto());
            rp.setPrecioProducto(p.getPrecioProducto());
            rp.setIdUsuario(p.getUsuario().getIdUsuario());
            rp.setCategorias(p.getCategorias());

            return rp;
        }).collect(Collectors.toList());
        return respuesta;
    }

    public RangoDePrecios obtenerRangoDePrecios() {
        return productoRepositorio.rango_precios();
    }

    public List<RespuestaProducto> obtenerProductosRelacionados(Long idProducto) {
        List<Producto> productos = productoRepositorio.findRelacionados(idProducto);

        List<RespuestaProducto> productosRespuesta = new ArrayList<>();
        for (Producto producto : productos) {
            RespuestaProducto respuesta = generarRespuesta(producto);
            productosRespuesta.add(respuesta);
        }

        return productosRespuesta;
    }//

    //. Construccion de respuesta
    public RespuestaProducto generarRespuesta(Producto producto) {
        RespuestaProducto respuestaProducto = new RespuestaProducto();

        respuestaProducto.setIdProducto(producto.getIdProducto());
        respuestaProducto.setNombreProducto(producto.getNombreProducto());
        respuestaProducto.setDescripcionProducto(producto.getDescripcionProducto());
        respuestaProducto.setRegionProducto(producto.getRegionProducto());
        respuestaProducto.setStockProducto(producto.getStockProducto());
        respuestaProducto.setPrecioProducto(producto.getPrecioProducto());
        respuestaProducto.setIdUsuario(producto.getUsuario().getIdUsuario());

        List<String> nombreCategorias = new ArrayList<>();
        List<CategoriaProducto> categorias = producto.getCategorias();
        for (CategoriaProducto categoria : categorias) {
            String nombre = categoria.getNombreCategoria();
            nombreCategorias.add(nombre);
        }

        respuestaProducto.setListaCategorias(nombreCategorias);

        List<ArchivoMultimedia> listaArchivos = archivoMultimediaServicio.listarArchivosPorEntidadYId(EntidadesArchivoMultimediaEnum.Producto, producto.getIdProducto());

        respuestaProducto.setListaArchivos(listaArchivos);

        return respuestaProducto;
    }


}
