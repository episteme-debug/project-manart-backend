package com.example.demo.Entidades;

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
    private Long idCarritoXProducto;

    @ManyToOne
    @JoinColumn(name = "idProducto", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "idCarrito", nullable = false)
    private CarritoCompra carritoCompra;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false, scale = 2, precision = 10)
    private BigDecimal precioUnitario;

    @Column(nullable = false, scale = 2, precision = 10)
    private BigDecimal subtotal;
}
