package com.example.demo.Entidades;
import com.example.demo.Enums.EstadoPedidoEnum;
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
    private Long idPedido;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoPedidoEnum estado = PENDIENTE;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDate fechaPedido;

    @Column(nullable = false, scale = 2)
    private BigDecimal total = BigDecimal.valueOf(0.00);

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<RelacionPedidoProducto> relacionPedidoProductos;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @OneToOne(mappedBy = "pedido")
    private Factura factura;


}
