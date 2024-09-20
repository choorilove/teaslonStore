package com.example.teastore;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;


import java.io.File;

public class S3Uploader {

    private AmazonS3 s3Client;

    public S3Uploader() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(
                System.getenv("AWS_ACCESS_KEY_ID"),
                System.getenv("AWS_SECRET_ACCESS_KEY")
        );

        this.s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(System.getenv("AWS_REGION"))
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
    }

    public void uploadFile(String fileName, File file) {
        String bucketName = System.getenv("S3_BUCKET_NAME");
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, file));
    }
}
//https://YOUR-BUCKET-NAME.s3.amazonaws.com/your-image.jpg