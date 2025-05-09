package com.example.pc120251backendseccion1.aplication;

import com.example.pc120251backendseccion1.domain.AuthService;
import com.example.pc120251backendseccion1.domain.Persona;
import com.example.pc120251backendseccion1.domain.PersonaService;
import com.example.pc120251backendseccion1.repository.PersonaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/personas")
public class PersonaController {
    private final PersonaService personaService;

    private final AuthService authService;

    public PersonaController(PersonaService personaService, AuthService authService, PersonaRepository personaRepository) {
        this.personaService = personaService;
        this.authService = authService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'REGISTRADOR')")
    public ResponseEntity<String> crearPersona(@RequestBody Persona persona) {
        authService.crearPersona(persona);
        return ResponseEntity.ok("Persona creada exitosamente");
    }

    @GetMapping("/{dni}")
    @PreAuthorize("hasAnyRole('ADMIN', 'REGISTRADOR', 'CONSULTOR')")
    public ResponseEntity<Optional<Persona>> consultarPersona(@PathVariable Long dni) {
        return ResponseEntity.ok(personaService.consultarPersona(dni));
    }

    @PutMapping("/{dni}/nombres")
    @PreAuthorize("hasAnyRole('ADMIN', 'REGISTRADOR')")
    public ResponseEntity<Persona> actualizarNombres(@PathVariable Long dni, @RequestBody Persona persona) {
        return ResponseEntity.ok(authService.actualizarNombres(dni, persona.getNombres(), persona.getApellidos()));
    }

    @PutMapping("/{dni}/padres")
    @PreAuthorize("hasAnyRole('ADMIN', 'REGISTRADOR')")
    public ResponseEntity<Persona> actualizarPadres(@PathVariable Long dni, @RequestBody Persona padres) {
        return ResponseEntity.ok(authService.actualizarPadres(dni, padres.getPadre(), padres.getMadre()));
    }

    @DeleteMapping("/{dni}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> eliminarPersona(@PathVariable Long dni) {
        authService.eliminarPersona(dni);
        return ResponseEntity.ok("Persona eliminada exitosamente");
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'REGISTRADOR', 'CONSULTOR')")
    public ResponseEntity<List<Persona>> listarPersonas() {
        return ResponseEntity.ok(personaService.listarPersonas());
    }

    @GetMapping("/{dni}/familia")
    @PreAuthorize("hasAnyRole('ADMIN', 'REGISTRADOR', 'CONSULTOR')")
    public ResponseEntity<Map<String, Persona>> consultarArbolFamiliar(@PathVariable Long dni) {
        return ResponseEntity.ok(personaService.consultarArbolFamiliar(dni));
    }
}
