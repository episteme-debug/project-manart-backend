package com.example.demo.Seguridad.Configuracion;

import com.example.demo.Entidades.Usuario;
import com.example.demo.Repositorios.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/* Esta anotación de Lombok crea un constructor automático que inicializa todos los campos final (en este caso, usuarioRepositorio), facilitando la inyección de dependencias*/
@RequiredArgsConstructor
/* indica que esta clase contiene definiciones de beans, es decir, objetos que Spring va a gestionar automáticamente*/
@Configuration
public class ApplicationConfig {

    private final UsuarioRepositorio usuarioRepositorio;

    // Se encarga de obtener el usuario desde la base de datos
    @Bean
    public UserDetailsService userDetailService()
    {
        return alias -> usuarioRepositorio.findByAlias(alias)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

    /* Crea un codificador de contraseñas que usa BCrypt, un algoritmo seguro para guardar contraseñas encriptadas, no en texto plano.*/
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*  Crea un proveedor de autenticación que:
        Usa tu UserDetailsService (para cargar usuarios).
        Usa tu PasswordEncoder (para verificar contraseñas)
        Este bean es el que Spring Security usa para validar al usuario cuando se loguea.*/
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
