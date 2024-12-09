package com.francisco.microservicios.app.usuarios.controller;

import com.francisco.microservicios.app.usuarios.models.Alumno;
import com.francisco.microservicios.app.usuarios.service.AlumnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AlumnoController {
    private AlumnoService service;

    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            return ResponseEntity.ok().body(service.findAll());
        } catch (Exception e) {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verAlumno(@PathVariable Long id) {
        try {
            Optional<Alumno> optionalAlumno = service.findById(id);
            if(optionalAlumno.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(optionalAlumno.get());
        } catch (Exception e) {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Alumno alumno) {
        try {
            Alumno alumnoRegistro = service.save(alumno);
            return ResponseEntity.status(HttpStatus.CREATED).body(alumnoRegistro);
        } catch (Exception e) {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Alumno alumno, @PathVariable Long id) {
        try {
            Optional<Alumno> optionalAlumno = service.findById(id);
            if(optionalAlumno.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            Alumno alumnoDb = optionalAlumno.get();
            alumnoDb.setName(alumno.getName());
            alumnoDb.setLastname(alumno.getLastname());
            alumnoDb.setName(alumno.getEmail());

            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDb));

        } catch (Exception e) {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar (@RequestBody@PathVariable Long id){
        try {
            Optional<Alumno> optionalAlumno = service.findById(id);
            if(optionalAlumno.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
