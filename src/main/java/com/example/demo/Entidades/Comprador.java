package com.example.demo.Entidades;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Comprador extends Usuario{

    @Column(nullable = false, length = 20)
    private String rolUsuario;

    @OneToMany(mappedBy = "comprador", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;

    public Comprador(Integer idUsuario, String nombreUsuario, String apellidoUsuario,
                    String telefonoUsuario, boolean estadoUsuario,
                    String imagenPerfilUsuario, int tipoUsuario)
    {
        super(idUsuario, nombreUsuario, apellidoUsuario, telefonoUsuario, estadoUsuario,
                imagenPerfilUsuario, tipoUsuario);
        this.rolUsuario = "Comprador";
    }

    public Comprador() {
    }

    public String getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(String rolUsuario) {
        this.rolUsuario = rolUsuario;
    }
}
