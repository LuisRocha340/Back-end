package Controller.impl;

import Controller.AlumnoApiController;
import Controller.model.Alumnos;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AlumnoApiControllerImpl implements AlumnoApiController {

    private Map<String, Alumnos> alumnosMap = new HashMap<>(); // Mapa para simular almacenamiento de alumnos

    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final String tokenPrefix = "Bearer ";
    private final String headerString = "Authorization";

    @Override
    public ResponseEntity<String> getName() {
        return ResponseEntity.ok().body("Edson Luis");
    }

    @Override
    public ResponseEntity<String> getName(@PathVariable String nombreAlumno) {
        return ResponseEntity.ok().body(nombreAlumno);
    }

    @Override
    public ResponseEntity<Alumnos> createAlumno(@RequestBody Alumnos alumnoNuevo) {
        alumnosMap.put(alumnoNuevo.getNombre(), alumnoNuevo); // Simulando almacenamiento
        return ResponseEntity.ok().body(alumnoNuevo);
    }

    @Override
    @PutMapping("/alumnos/{nombre}") // Endpoint para actualizar un alumno
    public ResponseEntity<String> updateAlumno(@PathVariable String nombre, @RequestBody Alumnos alumnoActualizado) {
        if (alumnosMap.containsKey(nombre)) {
            // Actualizar el alumno en el mapa (simulando almacenamiento)
            alumnosMap.put(nombre, alumnoActualizado);
            return ResponseEntity.ok().body("Alumno actualizado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El alumno no existe");
        }
    }

    @Override
    @DeleteMapping("/alumnos/{nombre}") // Endpoint para eliminar un alumno
    public ResponseEntity<String> deleteAlumno(@PathVariable String nombre) {
        if (alumnosMap.containsKey(nombre)) {
            // Eliminar el alumno del mapa (simulando almacenamiento)
            alumnosMap.remove(nombre);
            return ResponseEntity.ok().body("Alumno eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El alumno no existe");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        // Aquí deberías realizar la autenticación del usuario (por ejemplo, consultando una base de datos)
        // Si la autenticación es exitosa, genera un token JWT y lo devuelve al cliente

        if (username.equals("usuario") && password.equals("contraseña")) {
            String token = generateToken(username);
            return ResponseEntity.ok().body(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }

    private String generateToken(String username) {
        return tokenPrefix + Jwts.builder()
                .setSubject(username)
                .signWith(secretKey)
                .compact();
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyToken(@RequestHeader(name = "Authorization") String token) {
        // Verificar si el token está presente y es válido
        if (token != null && token.startsWith(tokenPrefix)) {
            String jwtToken = token.substring(tokenPrefix.length());
            try {
                Jws<Claims> claims = Jwts.parserBuilder()
                        .setSigningKey(secretKey)
                        .build()
                        .parseClaimsJws(jwtToken);

                // Si el token es válido, devuelve el nombre de usuario del token
                return ResponseEntity.ok().body(claims.getBody().getSubject());
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no proporcionado o incorrecto");
        }
    }
}
