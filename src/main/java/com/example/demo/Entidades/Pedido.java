package com.example.demo.Entidades;
import com.example.demo.Enums.EstadoPedidoEnum;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPedido;

    @Column(nullable = false )
    private LocalDate fechaPedido;

  /*  @Column(nullable = false)
    private LocalDate fechaUltimaModificacion = LocalDate.now();
*/

    @Column(nullable = false)
    private Enum<EstadoPedidoEnum> estado;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Comprador comprador;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<CarritoCompra> carritoCompras;

/*
  @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
    private Factura facturas;
*/

    public Pedido() {
    }

    public Pedido(Integer idPedido, LocalDate fechaPedido, LocalDate fechaUltimaModificacion, Enum<EstadoPedidoEnum> estado, Comprador comprador, List<CarritoCompra> carritoCompras) {
        this.idPedido = idPedido;
        this.fechaPedido = fechaPedido;
        this.estado = estado;
        this.comprador = comprador;
        this.carritoCompras = carritoCompras;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Enum<EstadoPedidoEnum> getEstado() {
        return estado;
    }

    public void setEstado(Enum<EstadoPedidoEnum> estado) {
        this.estado = estado;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public List<CarritoCompra> getCarritoCompras() {
        return carritoCompras;
    }

    public void setCarritoCompras(List<CarritoCompra> carritoCompras) {
        this.carritoCompras = carritoCompras;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", fechaPedido=" + fechaPedido +
               ", estado=" + estado +
                ", comprador=" + comprador +
                ", carritoCompras=" + carritoCompras +
                '}';
    }
}
