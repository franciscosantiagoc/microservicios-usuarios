package com.francisco.microservicios.app.usuarios.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "alumnos")
@Data
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastname;
    private String email;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }

}
