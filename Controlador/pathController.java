package com.lemon.CursoSpringBoot.Controlador;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
@RestController


public class pathController {


@GetMapping ("/queCuentas/{nombre}")
    public String variableDinamica (@PathVariable String nombre) {

        return "¿Qué cuentas "+nombre+ "?";

    }
}
