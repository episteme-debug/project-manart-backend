package com.example.demo.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CategoriaProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "El nombre de la categoría no puede estar vacío")
    @Size(max = 100, message = "El nombre de la categoría no debe exceder los 100 caracteres")
    private String nombreCategoria;

    @Column(nullable = false, length = 250)
    @NotBlank(message = "La descripción de la categoría no puede estar vacía")
    @Size(max = 250, message = "La descripción de la categoría no debe exceder los 250 caracteres")
    private String descripcionCategoria;

    @Column(nullable = false)
    @NotNull(message = "El estado de la categoría no puede ser nulo")
    private Boolean estadoCategoria = true;

    @Column(nullable = false, length = 150)
    @NotBlank(message = "La imagen de la categoría no puede estar vacía")
    @Size(max = 150, message = "El nombre de la imagen no debe exceder los 150 caracteres")
    private String imagenCategoria = "avatarGenerico.jpg";

    @ManyToMany(mappedBy = "categorias")
    private List<Producto> productos;
}