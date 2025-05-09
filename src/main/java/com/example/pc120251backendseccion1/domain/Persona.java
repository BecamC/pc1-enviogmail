package com.example.pc120251backendseccion1.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
public class Persona {
    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dni;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false)
    private Integer fechaNacimiento;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;

    @ManyToOne
    @JoinColumn(name = "padre_id")
    private Persona padre;

    @ManyToOne
    @JoinColumn(name = "madre_id")
    private Persona madre;

    @OneToMany(mappedBy = "padre")
    private List<Persona> hijosComoPadre;

    @OneToMany(mappedBy = "madre")
    private List<Persona> hijosComoMadre;
}
