package com.lemon.CursoSpringBoot.Controlador;

import com.lemon.CursoSpringBoot.model.Usuario;
import com.lemon.CursoSpringBoot.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Listar todos los usuarios
    @GetMapping("/list")
    public String list(Model model) {
        List<Usuario> usuarios = usuarioService.findAll();
        model.addAttribute("usuarios", usuarios);
        return "usuario-list";
    }

    // Mostrar formulario para crear usuario
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuario-form";
    }

    // Guardar usuario
    @PostMapping("/save")
    public String save(@ModelAttribute("usuario") Usuario usuario) {
        usuarioService.save(usuario);
        return "redirect:/usuarios/list";
    }

    // Mostrar formulario para editar usuario
    @GetMapping("/edit/{username}")
    public String showEditForm(@PathVariable String username, Model model) {
        Optional<Usuario> usuario = usuarioService.findById(username);
        if (usuario.isPresent()) {
            model.addAttribute("usuario", usuario.get());
            return "usuario-form";
        }
        return "redirect:/usuarios/list";
    }

    // Eliminar usuario
    @GetMapping("/delete/{username}")
    public String delete(@PathVariable String username) {
        usuarioService.deleteById(username);
        return "redirect:/usuarios/list";
    }

    // Reporte por dominio de email
    @GetMapping("/report/email")
    public String reportByEmail(@RequestParam String domain, Model model) {
        List<Usuario> usuarios = usuarioService.findByEmailDomain(domain);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("domain", domain);
        return "usuario-report-email";
    }
}