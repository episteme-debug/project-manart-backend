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
    private boolean estadoUsuario = true;

    @Column(nullable = false)
    private String imagenPerfilUsuario = "avatarGenerico.jpg";

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UsuarioEnum rolUsuario;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    List<Publicacion> publicaciones;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    List<Direccion> direcciones;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    List<CarritoCompra> carritoCompras;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    List<Pedido> pedido;


}
