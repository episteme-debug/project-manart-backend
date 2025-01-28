package com.example.demo.Entidades;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Artesano extends Usuario{

    @Column(nullable = false, length = 20)
    private String rolUsuario;

    @OneToMany(mappedBy = "artesano", cascade = CascadeType.ALL)
    private List<Producto> productos;

    public Artesano(Integer idUsuario, String nombreUsuario, String apellidoUsuario, String telefonoUsuario, boolean estadoUsuario, String imagenPerfilUsuario, int tipoUsuario, String emailUsuario, String contrasenaUsuario, String rolUsuario) {
        super(idUsuario, nombreUsuario, apellidoUsuario, telefonoUsuario, estadoUsuario, imagenPerfilUsuario, tipoUsuario, emailUsuario, contrasenaUsuario);
        this.rolUsuario = rolUsuario;
    }

    public Artesano() {
    }

    public String getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(String rolUsuario) {
        this.rolUsuario = rolUsuario;
    }


}
