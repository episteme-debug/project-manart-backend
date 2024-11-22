package com.example.demo.Entidades;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFactura;

    @Column(nullable = false )
    private LocalDate fechaEmisionFactura;

    @Column(nullable = false )
    private double pagoTotalFactura;

    @Column(nullable = false )
    private String estadoFactura;

    @Column(nullable = false )
    private String metodoPago;

    @OneToOne
    @JoinColumn(name = "idPedido", nullable = false)
    private Pedido pedido;

    public Factura() {
    }

    public Factura(Integer idFactura, LocalDate fechaEmisionFactura, double pagoTotalFactura, String estadoFactura, String metodoPago, Pedido pedido) {
        this.idFactura = idFactura;
        this.fechaEmisionFactura = fechaEmisionFactura;
        this.pagoTotalFactura = pagoTotalFactura;
        this.estadoFactura = estadoFactura;
        this.metodoPago = metodoPago;
        this.pedido = pedido;
    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }


    public LocalDate getFechaEmisionFactura() {
        return fechaEmisionFactura;
    }

    public void setFechaEmisionFactura(LocalDate fechaEmisionFactura) {
        this.fechaEmisionFactura = fechaEmisionFactura;
    }

    public double getPagoTotalFactura() {
        return pagoTotalFactura;
    }

    public void setPagoTotalFactura(double pagoTotalFactura) {
        this.pagoTotalFactura = pagoTotalFactura;
    }

    public String getEstadoFactura() {
        return estadoFactura;
    }

    public void setEstadoFactura(String estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "idFactura=" + idFactura +
                ", pedido=" + pedido +
                ", fechaEmisionFactura=" + fechaEmisionFactura +
                ", pagoTotalFactura=" + pagoTotalFactura +
                ", estadoFactura='" + estadoFactura + '\'' +
                ", metodoPago='" + metodoPago + '\'' +
                '}';
    }
}
