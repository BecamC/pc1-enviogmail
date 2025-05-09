package com.example.pc120251backendseccion1.domain;


import com.example.pc120251backendseccion1.repository.PersonaRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PersonaService {
    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public List<Persona> listarPersonas() {
        return personaRepository.findAll();
    }

    public Optional<Persona> consultarPersona(Long dni) {
        return personaRepository.findById(dni);
    }

    public Map<String, Persona> consultarArbolFamiliar(Long dni) {
        Persona persona = personaRepository.findById(dni)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        Map<String, Persona> arbolFamiliar = new HashMap<>();
        arbolFamiliar.put("persona", persona);
        arbolFamiliar.put("padre", persona.getPadre());
        arbolFamiliar.put("madre", persona.getMadre());
        if (persona.getPadre() != null) {
            arbolFamiliar.put("abueloPaterno", persona.getPadre().getPadre());
            arbolFamiliar.put("abuelaPaterna", persona.getPadre().getMadre());
        }
        if (persona.getMadre() != null) {
            arbolFamiliar.put("abueloMaterno", persona.getMadre().getPadre());
            arbolFamiliar.put("abuelaMaterna", persona.getMadre().getMadre());
        }
        return arbolFamiliar;
    }

}
