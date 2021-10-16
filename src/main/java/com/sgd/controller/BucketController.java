package com.sgd.controller;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bucket")
public class BucketController {

    @PostMapping("/{nameBucket}")
    public String createBucket(@PathVariable("nameBucket") String nameBucket){
        String bucketResponse = null;
        try{
            final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build();
            Bucket bucket = s3.createBucket(nameBucket);
            bucketResponse = "Nome do bucket criado: " + bucket.getName();
        }catch (AmazonS3Exception e){
            bucketResponse = e.getMessage();
        }finally {
            return bucketResponse;
        }
    }

}
