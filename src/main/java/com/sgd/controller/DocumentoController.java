package com.sgd.controller;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("documento")
public class DocumentoController {

    @GetMapping
    public String getDocumento(){
        return "objeto do documento";
    }

    @PostMapping(value = "/{nameBucket}/{nameObject}/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public String saveDocumento(@RequestParam("file") MultipartFile inputFile,
                                @PathVariable("nameBucket") String nameBucket,
                                @PathVariable("nameObject") String nameObject){
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build();
        String msgRetorno;
        try {
            s3.putObject(nameBucket, nameObject, convertMultiPartFileToFile(inputFile));
            msgRetorno = String.format("arquivo %s salvo no bucket %s", nameObject, nameBucket);
        }catch (Exception e) {
            msgRetorno = e.getMessage();
        }
        return msgRetorno;
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertedFile;
    }

}
