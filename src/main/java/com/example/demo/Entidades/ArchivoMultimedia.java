package com.example.demo.Entidades;

import com.example.demo.Enums.TipoArchivoEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ArchivoMultimedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoArchivoEnum tipo; // IMAGEN, VIDEO, AUDIO, etc.

    @Column(nullable = false)
    private String ruta; // ruta local o URL

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDate fechaCreacion;

    // Relación con Publicación
    @ManyToOne
    @JoinColumn(name = "idPublicacion", nullable = true)
    private Publicacion publicacion;

    // Relación con Producto
    @ManyToOne
    @JoinColumn(name = "idProducto", nullable = true)
    private Producto producto;

    // Relación con Categoría de Producto
    @ManyToOne
    @JoinColumn(name = "idCategoriaProducto", nullable = true)
    private CategoriaProducto categoriaProducto;

    // Relación con Usuario
    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = true)
    private Usuario usuario;

}
