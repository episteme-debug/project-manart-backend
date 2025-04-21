package com.example.demo.Entidades;
import com.example.demo.Enums.EstadoPedidoEnum;
import com.example.demo.Enums.MetodoPagoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.example.demo.Enums.EstadoPedidoEnum.PENDIENTE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPedido;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private Enum<EstadoPedidoEnum> estado = PENDIENTE;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MetodoPagoEnum metodoPago;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDate fechaPedido;

    @Column(nullable = false, scale = 2)
    private BigDecimal subtotal = BigDecimal.valueOf(0.00);

    @Column(nullable = false, scale = 2)
    private BigDecimal descuento = BigDecimal.valueOf(0.00);

    @Column(nullable = false, scale = 2)
    private BigDecimal total = BigDecimal.valueOf(0.00);

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<RelacionPedidoProducto> relacionPedidoProductos;

}
