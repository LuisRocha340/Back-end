package Controller;

import Controller.model.Alumnos;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/alumno")
public interface AlumnoApiController {
    @GetMapping("/")
    ResponseEntity<String> getName();

    @GetMapping("/{nombreAlumno}")
    ResponseEntity<String> getName(@PathVariable String nombreAlumno);

    @PostMapping("/")
    ResponseEntity<Alumnos> createAlumno(@RequestBody Alumnos alumnoNuevo);

    @PutMapping("/alumnos/{nombre}") // Endpoint para actualizar un alumno
    ResponseEntity<String> updateAlumno(@PathVariable String nombre, @RequestBody Alumnos alumnoActualizado);

    @DeleteMapping("/alumnos/{nombre}") // Endpoint para eliminar un alumno
    ResponseEntity<String> deleteAlumno(@PathVariable String nombre);
}
