package com.lemon.CursoSpringBoot.Controlador;


import com.lemon.CursoSpringBoot.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/custom-login")
    public String handleLogin(@RequestParam("username") String username, Model model) {
        Optional<com.lemon.CursoSpringBoot.model.Usuario> usuario = usuarioService.findById(username);
        if (usuario.isPresent()) {
            // Guardar el username en la sesión para usarlo en otras vistas
            model.addAttribute("currentUser", username);
            return "redirect:/gastos/list";
        } else {
            model.addAttribute("error", true);
            return "login";
        }
    }
}