package com.lemon.CursoSpringBoot.Controlador;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HelloWorldRestController {

@GetMapping ("/queOnda")
    public String HelloWord (){
        return "Yenni!!!!!, mi amiga cercana, ya empecé el trabjo jajajajaja";


    }
}
