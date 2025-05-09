package com.example.pc120251backendseccion1.domain;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Permisos> roles;

}
