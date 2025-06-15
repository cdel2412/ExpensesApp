package com.lemon.CursoSpringBoot.repository;

import com.lemon.CursoSpringBoot.model.Gastos;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface GastosRepository extends JpaRepository<Gastos, Long> {
    List<Gastos> findByFechaBetween(LocalDate startDate, LocalDate endDate);
    List<Gastos> findByNombreUsuario(String nombreUsuario);
    List<Gastos> findAll();
}