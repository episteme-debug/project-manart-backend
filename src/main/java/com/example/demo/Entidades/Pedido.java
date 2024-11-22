package com.example.demo.Entidades;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPedido;

    @Column(nullable = false )
    private int cantidad;

    @Column(nullable = false )
    private LocalDate fechaPedido;

    @Column(nullable = false )
    private double pagoTotalPedido;

    @Column(nullable = false )
    private String ubicacionPedido;

    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
    private Factura facturas;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Comprador comprador;

    @ManyToMany
    @JoinTable(
            name = "pedidoxproducto",
            joinColumns = @JoinColumn(name = "idPedido"),
            inverseJoinColumns = @JoinColumn(name = "idProducto")
    )
    private List<Producto> productos;


    public Pedido() {
    }

    public Pedido(Integer idPedido, int cantidad, LocalDate fechaPedido, double pagoTotalPedido, String ubicacionPedido, Factura facturas, Comprador comprador, List<Producto> productos) {
        this.idPedido = idPedido;
        this.cantidad = cantidad;
        this.fechaPedido = fechaPedido;
        this.pagoTotalPedido = pagoTotalPedido;
        this.ubicacionPedido = ubicacionPedido;
        this.facturas = facturas;
        this.comprador = comprador;
        this.productos = productos;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public double getPagoTotalPedido() {
        return pagoTotalPedido;
    }

    public void setPagoTotalPedido(double pagoTotalPedido) {
        this.pagoTotalPedido = pagoTotalPedido;
    }

    public String getUbicacionPedido() {
        return ubicacionPedido;
    }

    public void setUbicacionPedido(String ubicacionPedido) {
        this.ubicacionPedido = ubicacionPedido;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", comprador=" + comprador +
                ", cantidad=" + cantidad +
                ", fechaPedido=" + fechaPedido +
                ", pagoTotalPedido=" + pagoTotalPedido +
                ", ubicacionPedido='" + ubicacionPedido + '\'' +
                ", facturas=" + facturas +
                '}';
    }
}
