package com.avijit.filestoragesystem.Controller.FileStorageControllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class FileStorage {

    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFile(){

        return ResponseEntity.ok().body("File uploaded successfully");
    }

    @GetMapping("/downloadFile")
    public ResponseEntity<?> downloadFile(){

        return ResponseEntity.ok().body("File downloaded successfully");
    }

}
