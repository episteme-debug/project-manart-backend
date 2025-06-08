package com.example.demo.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
public class FacturaProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItem;

    @Column(nullable = false)
    private Long idProducto;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false)
    private String unidadMedida = "UND";

    @Column(nullable = false)
    private BigDecimal precioUnitario;

    @Column
    private BigDecimal precioTotal;

    @ManyToOne
    @JoinColumn(name = "idFactura", nullable = false)
    private Factura factura;
}
