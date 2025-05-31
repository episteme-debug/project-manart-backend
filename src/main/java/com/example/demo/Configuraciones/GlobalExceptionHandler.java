package com.example.demo.Configuraciones;

import com.example.demo.DTOs.ApiMensaje;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiMensaje> handleValidationException(MethodArgumentNotValidException e) {
        String mensajeError = Objects.requireNonNull(e.getBindingResult().getFieldError().getDefaultMessage());
        return ResponseEntity.badRequest().body(new ApiMensaje(mensajeError));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiMensaje> handleGeneralException(Exception e) {
        String mensajeError = e.getMessage();
        return ResponseEntity.internalServerError().body(new ApiMensaje(mensajeError));
    }
}
