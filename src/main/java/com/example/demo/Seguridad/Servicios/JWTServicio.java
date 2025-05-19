package com.example.demo.Seguridad.Servicios;

import com.example.demo.Entidades.Usuario;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTServicio {

    // Clave secreta utilizada para firmar y verificar los JWT. Se recomienda que esté codificada en Base64.
    @Value("${jwt.clavesecreta}")
    private String CLAVE_SECRETA;

    /**
     * Método público que genera un token JWT para un usuario dado.
     * @param usuario - objeto UserDetails (información del usuario)
     * @return un token JWT en forma de String
     */
    public String generarToken(Usuario usuario) {
        return generarToken(new HashMap<>(), usuario); // No añade claims extra aquí (envía un HashMap vacío).
    }

    /**
     * Método privado que genera un JWT real.
     * @param extraClaims - mapa de claims (información adicional que quieras guardar en el token)
     * @param usuario - objeto UserDetails
     * @return un token JWT firmado
     */
    private String generarToken(Map<String, Object> extraClaims, Usuario usuario) {
        return Jwts
                .builder() // Constructor del JWT
                .claims(extraClaims) // Establece claims adicionales (puede ser vacío).
                .claim("idUsuario", usuario.getIdUsuario())
                .claim("emailUsuario", usuario.getEmailUsuario())
                .subject(usuario.getUsername()) // Establece el "subject" (normalmente, el nombre de usuario).
                .issuedAt(new Date(System.currentTimeMillis())) // Fecha de emisión (ahora).
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) // Expira en 24 minutos.
                .signWith(generarClave()) // Firma el token usando la clave y el algoritmo HS256.
                .compact(); // Compacta todo en un String JWT.
    }

    /**
     * Método privado que genera la clave de firma del token.
     * @return una clave Key utilizando el algoritmo HMAC SHA.
     */
    private SecretKey generarClave() {
        byte[] keyBytes = Decoders.BASE64.decode(CLAVE_SECRETA); // Decodifica la clave secreta en Base64.
        return Keys.hmacShaKeyFor(keyBytes); // Genera una clave HMAC-SHA válida.
    }

    /**
     * Método público para extraer el nombre de usuario (subject) del token.
     * @param token - el JWT del cual extraer el nombre de usuario.
     * @return el username extraído del token.
     */
    public String extraerNombreUsuario(String token) {
        return obtenerClaim(token, Claims::getSubject); // Usa Claims::getSubject para obtener el campo 'sub' del token.
    }

    /**
     * Método público para validar si un token pertenece a un usuario y si no ha expirado.
     * @param token - el JWT a validar
     * @param userDetails - los datos del usuario para comparar
     * @return true si es válido, false si no.
     */
    public boolean validarToken(String token, UserDetails userDetails) {
        final String username = extraerNombreUsuario(token);
        return (username.equals(userDetails.getUsername()) && !tokenExpirado(token));
    }

    /**
     * Método privado para listar (obtener) todos los claims contenidos en un token.
     * @param token - el JWT del cual obtener los claims
     * @return Claims - el cuerpo del token donde están todos los datos.
     */
    private Claims listarTodosClaims(String token) {
        return Jwts
                .parser() // Creador del parser
                .verifyWith(generarClave()) // Establece la clave de firma para verificar el token.
                .build()
                .parseSignedClaims(token) // Parsea el JWT y verifica su firma.
                .getPayload(); // Obtiene el cuerpo (claims) del JWT.
    }

    /**
     * Método público genérico para obtener un claim específico del token usando una función de resolución.
     * @param token - el JWT
     * @param claimsResolver - una función para extraer el claim deseado.
     * @return el valor extraído del claim.
     */
    public <T> T obtenerClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = listarTodosClaims(token); // Obtiene todos los claims.
        return claimsResolver.apply(claims); // Aplica la función para obtener el claim deseado.
    }

    /**
     * Método privado para obtener la fecha de expiración del token.
     * @param token - el JWT
     * @return fecha de expiración del token
     */
    private Date obtenerFechaExpiracion(String token) {
        return obtenerClaim(token, Claims::getExpiration); // Extrae el campo 'exp' del token.
    }

    /**
     * Método privado para saber si el token ya expiró.
     * @param token - el JWT
     * @return true si ya expiró, false si aún es válido.
     */
    private boolean tokenExpirado(String token) {
        return obtenerFechaExpiracion(token).before(new Date()); // Compara si la fecha de expiración ya pasó.
    }
}