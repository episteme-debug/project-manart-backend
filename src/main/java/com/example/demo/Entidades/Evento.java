package com.example.demo.Entidades;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Evento {

    //Definición de atributos y relaciones
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEvento;

    @Column(nullable = false, length = 50)
    private String nombreEvento;

    @Column(nullable = false, length = 200)
    private String descripcionEvento;

    @Column(nullable = false, length = 50)
    private LocalDate fechaEvento;

    @Column(nullable = false, length = 50)
    private String ubicacionEvento;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @Column(nullable = false, length = 50)
    private String tipoEvento;

    @Column(nullable = false, length = 50)
    private boolean estadoEvento;


}
