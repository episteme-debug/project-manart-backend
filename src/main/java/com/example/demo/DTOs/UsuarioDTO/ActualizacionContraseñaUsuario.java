package com.example.demo.DTOs.UsuarioDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActualizacionContraseñaUsuario {

    String contraseñaAntigua;
    String contraseñaNueva;

}
