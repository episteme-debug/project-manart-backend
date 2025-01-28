package com.example.demo.Entidades;
import jakarta.persistence.Column;
import jakarta.persistence.*;

@Entity
public class CarritoCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCarrito;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false)
    private int total;

    @ManyToOne
    @JoinColumn(name = "idPedido", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "idProducto", nullable = false)
    private Producto producto;

    public CarritoCompra() {
    }

    public CarritoCompra(Integer idCarrito, int cantidad, int total, Pedido pedido) {
        this.idCarrito = idCarrito;
        this.cantidad = cantidad;
        this.total = total;
        this.pedido = pedido;
    }


}
