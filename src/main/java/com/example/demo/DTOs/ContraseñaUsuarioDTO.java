package com.example.demo.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContraseñaUsuarioDTO {

    String contraseñaAntigua;
    String contraseñaNueva;

}
