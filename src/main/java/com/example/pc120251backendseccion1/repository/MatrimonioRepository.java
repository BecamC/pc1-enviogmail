package com.example.pc120251backendseccion1.repository;

import com.example.pc120251backendseccion1.domain.Matrimonio;
import com.example.pc120251backendseccion1.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatrimonioRepository extends JpaRepository<Matrimonio, Long> {

}
