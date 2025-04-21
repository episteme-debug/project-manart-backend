package com.example.demo.Entidades;

import com.example.demo.Enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(nullable = false, length = 100)
    private String nombreUsuario;

    @Column(nullable = false, length = 100)
    private String apellidoUsuario;

    @Column(nullable = false, length = 200)
    private String emailUsuario;

    @Column(nullable = false, length = 250)
    private String hashContrasenaUsuario;

    @Column(nullable = false, length = 20)
    private String telefonoUsuario;

    @Column(nullable = false)
    private Boolean estadoUsuario = true;

    @Column(nullable = false)
    private String imagenPerfilUsuario = "avatarGenerico.jpg";

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UsuarioEnum rolUsuario;

    // Relaciones
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Producto> productos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Publicacion> publicaciones;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Direccion> direcciones;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarritoCompra> carritoCompras;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedido;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArchivoMultimedia> archivosMultimedia;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReseñaProducto> reseñaProducto;
}
