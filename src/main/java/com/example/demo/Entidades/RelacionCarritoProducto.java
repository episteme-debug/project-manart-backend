package com.example.demo.Entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelacionCarritoProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItem;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false, scale = 2)
    private BigDecimal precioUnitario = BigDecimal.valueOf(0.00);

    @Column(nullable = false, scale = 2)
    private BigDecimal subtotal = BigDecimal.valueOf(0.00);

    @ManyToOne
    @JoinColumn(name = "idProducto", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "idCarrito", nullable = false)
    private CarritoCompra carritoCompra;
}
