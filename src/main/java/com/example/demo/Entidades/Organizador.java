package com.example.demo.Entidades;
import jakarta.persistence.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.List;

@Entity
public class Organizador extends Usuario{

    @Column(nullable = false, length = 20)
    private String rolUsuario;

    @OneToMany(mappedBy = "organizador", cascade = CascadeType.ALL)
    List<Evento> eventos;

    public Organizador(Integer idUsuario, String nombreUsuario, String apellidoUsuario,
                    String telefonoUsuario, boolean estadoUsuario,
                    String imagenPerfilUsuario, int tipoUsuario)
    {
        super(idUsuario, nombreUsuario, apellidoUsuario, telefonoUsuario, estadoUsuario,
                imagenPerfilUsuario, tipoUsuario);
        this.rolUsuario = "Organizador";
    }

    public Organizador() {
    }

    public String getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(String rolUsuario) {
        this.rolUsuario = rolUsuario;
    }
}
