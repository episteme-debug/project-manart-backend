package com.example.demo.Entidades;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CarritoCompra<C> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarrito;

    @ManyToOne(targetEntity = Usuario.class)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @Column(nullable = false, scale = 2)
    private BigDecimal total = BigDecimal.valueOf(0.00);

    @OneToMany(mappedBy = "carritoCompra", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<RelacionCarritoProducto> relacionCarritoProductos;
}
