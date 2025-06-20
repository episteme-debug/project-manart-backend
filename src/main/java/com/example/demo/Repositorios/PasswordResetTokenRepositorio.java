package com.example.demo.Repositorios;

import com.example.demo.Entidades.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepositorio extends JpaRepository<PasswordResetToken,Long> {
    Optional<PasswordResetToken> findByToken(String token);
}
