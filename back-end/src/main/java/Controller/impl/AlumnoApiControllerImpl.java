package Controller.impl;

import Controller.AlumnoApiController;
import Controller.model.Alumnos;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlumnoApiControllerImpl implements AlumnoApiController {
    @Override
    public ResponseEntity<String> getName() {
        return ResponseEntity.ok().body("Edson Luis");
    }

    @Override
    public ResponseEntity <String> getName(@PathVariable String nombreAlumno) {
        return ResponseEntity.ok().body(nombreAlumno);
    }
    @Override
    public ResponseEntity<Alumnos> createAlumno(@RequestBody Alumnos alumnoNuevo){
        return ResponseEntity.ok().body(alumnoNuevo);
    }

    private Alumnos alumnoDummy(){
        Alumnos alumnoDummy = new Alumnos();
        return  alumnoDummy;
    }
}
