package com.sgd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("documento")
public class DocumentoController {

    @GetMapping
    public String getDocumento(){
        return "objeto do documento";
    }

    @PostMapping
    public String saveDocumento(){
        return "documento Salvo";
    }

}
