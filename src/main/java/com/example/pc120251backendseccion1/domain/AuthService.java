package com.example.pc120251backendseccion1.domain;

import com.example.pc120251backendseccion1.repository.PersonaRepository;

import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthService {
    private final PersonaRepository personaRepository;

    public AuthService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public Persona crearPersona(Persona persona) {
        return personaRepository.save(persona);
    }

    public Persona actualizarNombres(Long dni, String nombres, String apellidos) {
        Persona persona = personaRepository.findById(dni)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));
        persona.setNombres(nombres);
        persona.setApellidos(apellidos);
        return personaRepository.save(persona);
    }

    public Persona actualizarPadres(Long dni, Persona padre, Persona madre) {
        Persona persona = personaRepository.findById(dni)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));
        persona.setPadre(padre);
        persona.setMadre(madre);
        return personaRepository.save(persona);
    }

    public void eliminarPersona(Long dni) {
        Persona persona = personaRepository.findById(dni)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));
        personaRepository.delete(persona);
    }
}