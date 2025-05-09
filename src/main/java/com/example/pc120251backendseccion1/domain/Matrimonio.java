package com.example.pc120251backendseccion1.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Matrimonio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "esposo_id", nullable = false)
    private Persona esposo;

    @ManyToOne
    @JoinColumn(name = "esposa_id", nullable = false)
    private Persona esposa;

    @Column(nullable = false)
    private String fechaMatrimonio;

    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;
}
