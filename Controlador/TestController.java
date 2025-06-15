package com.lemon.CursoSpringBoot.Controlador;
import com.lemon.CursoSpringBoot.model.Usuario;
import com.lemon.CursoSpringBoot.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/test/usuarios")
    public List<Usuario> getAllUsuarios() {
        return usuarioService.findAll();
    }
}