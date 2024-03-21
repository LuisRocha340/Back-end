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
}
