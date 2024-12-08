package com.francisco.microservicios.app.usuarios.repository;

import com.francisco.microservicios.app.usuarios.models.Alumno;
import org.springframework.data.repository.CrudRepository;

public interface AlumnoRepository extends CrudRepository<Alumno, Long> {
    
}
