package com.example.demo.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromocionDTO {
    private Long idPromocion;
    private String nombrePromocion;
    private String detallesPromocion;
    private LocalDate fechaInicioPromocion;
    private LocalDate fechaFinPromocion;
    private Integer porcentajeDescuentoPromocion;
    private Boolean estadoPromocion;
}
