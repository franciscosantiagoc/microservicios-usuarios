package com.francisco.microservicios.app.usuarios.controller;

import com.francisco.microservicios.app.usuarios.models.Alumno;
import com.francisco.microservicios.app.usuarios.service.AlumnoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
