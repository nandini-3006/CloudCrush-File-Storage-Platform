package com.CloudCrush.metadata_service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "storage-service", url = "http://storage-service:8083/api/storage")
public interface StorageClient {

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile file);
    @GetMapping("/download")
    ResponseEntity<byte[]> downloadFile(@RequestParam("url") String fileUrl);


    @PostMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> deleteFile(@RequestBody DTO request);

}