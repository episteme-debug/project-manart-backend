package com.example.demo.Entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDireccion;

    @Column(nullable = false)
    private String tipoVia;

    @Column(nullable = false)
    private String numeroViaPrincipal;

    @Column(nullable = true)
    private String letraViaPrincipal;

    @Column(nullable = true)
    private Boolean bisViaPrincipal;

    @Column(nullable = false)
    private String numeroViaSecundaria;

    @Column(nullable = true)
    private String letraViaSecundaria;

    @Column(nullable = true)
    private Boolean bisViaSecundaria;

    @Column(nullable = false)
    private String numeroPredio;

    @Column(nullable = true)
    private String complemento;

    @Column(nullable = false)
    private String barrio;

    @Column(nullable = false)
    private String ciudad;

    @Column(nullable = false)
    private String departamento;

    @Column(nullable = false)
    private Boolean esPredeterminada;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDate fechaCreacion;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDate fechaActualizacion;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

}
