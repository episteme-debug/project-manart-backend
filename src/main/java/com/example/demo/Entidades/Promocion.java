package com.example.demo.Entidades;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Promocion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPromocion;

    @Column(nullable = false, length = 100)
    private String nombrePromocion;

    @Column(nullable = false, length = 200)
    private String detallesPromocion;

    @Column(nullable = false)
    private LocalDate fechaInicioPromocion;

    @Column(nullable = false)
    private LocalDate fechaFinPromocion;

    @Column(nullable = false, length=50)
    private Integer porcentajeDescuentoPromocion;

    @Column(nullable = false)
    private Boolean estadoPromocion;

    @JsonIgnore
    @OneToMany(mappedBy = "promocion", cascade = CascadeType.ALL)
    List<Producto> producto;

}
