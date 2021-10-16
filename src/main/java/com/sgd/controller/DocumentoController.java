package com.sgd.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("documento")
public class DocumentoController {

    @GetMapping
    public String getDocumento(){
        return "objeto do documento";
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String saveDocumento(@RequestParam("file") MultipartFile inputFile){
        return "documento Salvo";
    }

}
