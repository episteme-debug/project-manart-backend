package com.example.demo.Seguridad.Configuracion;

import com.example.demo.Seguridad.Filtros.JWTAuthenticatorFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JWTAuthenticatorFilter jwtAuthenticatorFilter;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        return http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf
                        .disable())
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                /*Solo rutas con public se les permite el acceso, el resto tienen que autenticarse*/
                                .requestMatchers(
                                        "/api/archivomultimedia/public/**",
                                        "/api/autenticacion/public/**",
                                        "/api/carrito/public/**",
                                        "/api/categoriaproducto/public/**",
                                        "/api/direccion/public/**",
                                        "/api/factura/public/**",
                                        "/api/pago/public/**",
                                        "/api/pedido/public/**",
                                        "/api/producto/public/**",
                                        "/api/promocion/public/**",
                                        "/api/publicacion/public/**",
                                        "/api/relcarritoproducto/public/**",
                                        "/api/usuarios/public/**"
                                ).permitAll()

                                .requestMatchers(
                                        "/api/archivomultimedia/private/**",
                                        "/api/autenticacion/private/**",
                                        "/api/carrito/private/**",
                                        "/api/categoriaproducto/private/**",
                                        "/api/direccion/private/**",
                                        "/api/factura/private/**",
                                        "/api/pago/private/**",
                                        "/api/pedido/private/**",
                                        "/api/producto/private/**",
                                        "/api/promocion/private/**",
                                        "/api/publicacion/private/**",
                                        "/api/relcarritoproducto/private/**",
                                        "/api/usuarios/private/**"
                                ).authenticated()
                                .anyRequest().permitAll())
                .oauth2Login(oauth -> oauth
                        .successHandler(oAuth2SuccessHandler))
                .sessionManagement(sessionManager ->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticatorFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
