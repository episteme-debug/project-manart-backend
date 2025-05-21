package com.example.demo.DTOs.AuthDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogIn {
    String alias;
    String contraseña;
}
