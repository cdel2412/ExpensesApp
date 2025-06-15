package com.lemon.CursoSpringBoot.repository;

import com.lemon.CursoSpringBoot.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    List<Usuario> findByEmailContaining(String emailDomain);
    List<Usuario> findAll();
}