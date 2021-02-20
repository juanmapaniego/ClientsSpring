package com.jmpaniego.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class ClientController {
    @GetMapping
    public String inicio(){
        log.info("Ejecutando el controlador");
        return "index";
    }



}
