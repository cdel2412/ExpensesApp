package com.lemon.CursoSpringBoot.Controlador;
import com.lemon.CursoSpringBoot.model.Usuario;
import com.lemon.CursoSpringBoot.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.UUID;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/forgot-password")
    public String showForgotPasswordForm() {
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String processForgotPassword(@RequestParam String email, Model model) {
        Optional<Usuario> usuarioOpt = usuarioService.findAll().stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst();
        if (usuarioOpt.isPresent()) {
            // Generar token (en una app real, almacenar en BD)
            String token = UUID.randomUUID().toString();
            // Enviar email
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Restablecer Contraseña");
            message.setText("Haz clic para restablecer tu contraseña: http://localhost:8080/reset-password?token=" + token);
            mailSender.send(message);
            model.addAttribute("message", "Se ha enviado un enlace a tu email.");
        } else {
            model.addAttribute("error", "Email no encontrado.");
        }
        return "forgot-password";
    }

    @GetMapping("/reset-password")
    public String showResetPasswordForm(@RequestParam String token, Model model) {
        // En una app real, validar token
        model.addAttribute("token", token);
        return "reset-password";
    }

    @PostMapping("/reset-password")
    public String processResetPassword(@RequestParam String token, @RequestParam String password, Model model) {
        // En una app real, validar token y actualizar contraseña
        model.addAttribute("message", "Contraseña actualizada (simulación).");
        return "reset-password";
    }
}