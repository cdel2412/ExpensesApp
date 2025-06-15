package com.lemon.CursoSpringBoot.Controlador;

import com.lemon.CursoSpringBoot.model.Gastos;
import com.lemon.CursoSpringBoot.model.Usuario;
import com.lemon.CursoSpringBoot.service.GastosService;
import com.lemon.CursoSpringBoot.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/gastos")
public class GastosController {

    @Autowired
    private GastosService gastosService;

    @Autowired
    private UsuarioService usuarioService;

    // Listar todos los gastos
    @GetMapping("/list")
    public String list(Model model) {
        List<Gastos> gastos = gastosService.findAll();
        model.addAttribute("gastos", gastos);
        return "gastos-list";
    }

    // Mostrar formulario para crear gasto
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("gasto", new Gastos());
        model.addAttribute("usuarios", usuarioService.findAll());
        return "gastos-form";
    }

    // Guardar gasto
    @PostMapping("/save")
    public String save(@ModelAttribute("gasto") Gastos gasto) {
        gastosService.save(gasto);
        return "redirect:/gastos/list";
    }

    // Mostrar formulario para editar gasto
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Gastos> gasto = gastosService.findById(id);
        if (gasto.isPresent()) {
            model.addAttribute("gasto", gasto.get());
            model.addAttribute("usuarios", usuarioService.findAll());
            return "gastos-form";
        }
        return "redirect:/gastos/list";
    }

    // Eliminar gasto
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        gastosService.deleteById(id);
        return "redirect:/gastos/list";
    }

    // Reporte por rango de fechas
    @GetMapping("/report/fecha")
    public String reportByFecha(@RequestParam String startDate, @RequestParam String endDate, Model model) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        List<Gastos> gastos = gastosService.findByFechaBetween(start, end);
        model.addAttribute("gastos", gastos);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        return "gastos-report-fecha";
    }

    // Reporte por usuario
    @GetMapping("/report/usuario")
    public String reportByUsuario(@RequestParam String nombreUsuario, Model model) {
        List<Gastos> gastos = gastosService.findByNombreUsuario(nombreUsuario);
        model.addAttribute("gastos", gastos);
        model.addAttribute("nombreUsuario", nombreUsuario);
        return "gastos-report-usuario";
    }
}