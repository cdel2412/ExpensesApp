package com.lemon.CursoSpringBoot.service;
import com.lemon.CursoSpringBoot.model.Gastos;
import com.lemon.CursoSpringBoot.repository.GastosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class GastosService {

    @Autowired
    private GastosRepository gastosRepository;

    // CRUD Operations
    public List<Gastos> findAll() {
        return gastosRepository.findAll();
    }

    public Optional<Gastos> findById(Long id) {
        return gastosRepository.findById(id);
    }

    public Gastos save(Gastos gastos) {
        return gastosRepository.save(gastos);
    }

    public void deleteById(Long id) {
        gastosRepository.deleteById(id);
    }

    // Reportes Parametrizados
    public List<Gastos> findByFechaBetween(LocalDate startDate, LocalDate endDate) {
        return gastosRepository.findByFechaBetween(startDate, endDate);
    }

    public List<Gastos> findByNombreUsuario(String nombreUsuario) {
        return gastosRepository.findByNombreUsuario(nombreUsuario);
    }
}