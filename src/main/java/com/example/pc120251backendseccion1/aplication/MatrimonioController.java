package com.example.pc120251backendseccion1.aplication;

import com.example.pc120251backendseccion1.domain.MatrimonioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matrimonios")
public class MatrimonioController {
    private final MatrimonioService matrimonioService;

    public MatrimonioController(MatrimonioService matrimonioService) {
        this.matrimonioService = matrimonioService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'REGISTRADOR')")
    public ResponseEntity<String> registrarMatrimonio(@RequestParam Long dni1, @RequestParam Long dni2) {
        matrimonioService.registrarMatrimonio(dni1, dni2);
        return ResponseEntity.ok("Matrimonio registrado exitosamente");
    }


    @GetMapping("/validar/{dni1}/{dni2}")
    @PreAuthorize("hasAnyRole('ADMIN', 'REGISTRADOR', 'CONSULTOR')")
    public ResponseEntity<String> validarImpedimentos(@PathVariable Long dni1, @PathVariable Long dni2) {
        boolean impedimentos = matrimonioService.validarImpedimentos(dni1, dni2);
        return ResponseEntity.ok(impedimentos ? "Existen impedimentos matrimoniales" : "No existen impedimentos matrimoniales");
    }
}
