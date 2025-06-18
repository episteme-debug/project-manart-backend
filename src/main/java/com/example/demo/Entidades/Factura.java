package com.example.demo.Entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFactura;

    @Column(nullable = false)
    private String CUFE = "";

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime fechaGeneracionFactura;

    @Column(nullable = false)
    private int totalItems = 0;

    @Column(nullable = false, scale = 2)
    @DecimalMin("0.00")
    private BigDecimal totalBruto;

    @Column(nullable = false, scale = 2)
    @DecimalMin("0.00")
    private BigDecimal IVA;

    @Column(nullable = false, scale = 2)
    @DecimalMin("0.00")
    private BigDecimal totalNeto;

    @Column(nullable = false)
    @Size(max = 30)
    private String metodoPago;

    @Column(nullable = false)
    @Size(max = 200)
    private String valorLetras;

    @Column(nullable = false)
    @Size(max = 255)
    private String observaciones;

    // Info cliente
    @Column(nullable = false, precision = 2)
    @Size(max = 100)
    private String nombreCliente;

    @Column(nullable = true)
    @Size(max = 20)
    private String nitCliente;

    @Column(nullable = false)
    @Size(max = 100)
    private String direccionCliente;

    @Column(nullable = false)
    @Size(max = 50)
    private String ciudadCliente;

    @Column(nullable = false)
    @Size(max = 100)
    private String emailCliente;

    @Column(nullable = true)
    @Size(max = 20)
    private String telefonoCliente;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<FacturaProducto> facturaProductos;

    @OneToOne
    private Pedido pedido;

}
