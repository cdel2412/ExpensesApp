package com.lemon.CursoSpringBoot.service;

import com.lemon.CursoSpringBoot.model.Usuario;
import com.lemon.CursoSpringBoot.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // CRUD Operations
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(String username) {
        return usuarioRepository.findById(username);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deleteById(String username) {
        usuarioRepository.deleteById(username);
    }

    // Reportes Parametrizados
    public List<Usuario> findByEmailDomain(String emailDomain) {
        return usuarioRepository.findByEmailContaining(emailDomain);
    }
}