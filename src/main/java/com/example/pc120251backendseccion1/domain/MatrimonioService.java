package com.example.pc120251backendseccion1.domain;


import com.example.pc120251backendseccion1.repository.MatrimonioRepository;
import com.example.pc120251backendseccion1.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class MatrimonioService {
    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private MatrimonioRepository matrimonioRepository;

    public void registrarMatrimonio(Long dni1, Long dni2) {
        Persona esposo = personaRepository.findById(dni1)
                .orElseThrow(() -> new RuntimeException("Esposo no encontrado"));
        Persona esposa = personaRepository.findById(dni2)
                .orElseThrow(() -> new RuntimeException("Esposa no encontrada"));

        validarImpedimentos(esposo.getDni(), esposa.getDni());

        Matrimonio matrimonio = new Matrimonio();
        matrimonio.setEsposo(esposo);
        matrimonio.setEsposa(esposa);

        matrimonioRepository.save(matrimonio);
    }

    public boolean validarImpedimentos(Long dni1, Long dni2) {
        Persona esposo = personaRepository.findById(dni1)
                .orElseThrow(() -> new RuntimeException("Esposo no encontrado"));
        Persona esposa = personaRepository.findById(dni2)
                .orElseThrow(() -> new RuntimeException("Esposa no encontrada"));

        try {
            validarImpedimentosLegales(esposo, esposa);
            return false; // No hay impedimentos
        } catch (RuntimeException e) {
            return true; // Hay impedimentos
        }
    }

    private void validarImpedimentosLegales(Persona esposo, Persona esposa) {
        if (esposo == null || esposa == null) {
            throw new RuntimeException("Ambos contrayentes deben estar registrados.");
        }

        if (esposo.getFechaNacimiento() > 2005 || esposa.getFechaNacimiento() > 2005) {
            throw new RuntimeException("No se permite el matrimonio con menores de edad.");
        }

        if (esposo.getPadre() != null && esposo.getPadre().equals(esposa)) {
            throw new RuntimeException("No se permite el matrimonio entre ascendientes y descendientes.");
        }
        if (esposo.getMadre() != null && esposo.getMadre().equals(esposa)) {
            throw new RuntimeException("No se permite el matrimonio entre ascendientes y descendientes.");
        }

        if (esposo.getPadre() != null && esposo.getPadre().getHijosComoPadre().contains(esposa)) {
            throw new RuntimeException("No se permite el matrimonio entre hermanos.");
        }
        if (esposo.getMadre() != null && esposo.getMadre().getHijosComoMadre().contains(esposa)) {
            throw new RuntimeException("No se permite el matrimonio entre hermanos.");
        }
        if (esposo.getPadre() != null && esposo.getPadre().getPadre() != null &&
                esposo.getPadre().getPadre().getHijosComoPadre().contains(esposa)) {
            throw new RuntimeException("No se permite el matrimonio entre tíos y sobrinos.");
        }

        if (esposo.getEstadoCivil() == EstadoCivil.CASADO || esposa.getEstadoCivil() == EstadoCivil.CASADO) {
            throw new RuntimeException("No se permite el matrimonio si alguno de los contrayentes ya está casado.");
        }
    }
}
