package com.example.demo.Repositorios;

import com.example.demo.Entidades.Usuario;
import com.example.demo.Enums.UsuarioEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>
{
    Optional<Usuario> findByEmailUsuario(String emailUsuario);

    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

    List<Usuario> findByEstadoUsuario(Boolean estadoUsuario);

    List<Usuario> findByRolUsuario(Enum<UsuarioEnum> rolUsuario);

    boolean existsByNombreUsuario(String nombreUsuario);
}
