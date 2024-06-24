package com.avijit.filestoragesystem.Controller.FileStorageControllers;

import com.avijit.filestoragesystem.Service.FileStorageService.FileStorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class FileStorageController {
   private final FileStorageService fileStorageService;

    public FileStorageController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFile(@RequestParam("file") String file, @RequestParam("type") String type){

        return ResponseEntity.ok().body("File uploaded successfully");
    }

    @GetMapping("/downloadFile")
    public ResponseEntity<?> downloadFile(){

        return ResponseEntity.ok().body("File downloaded successfully");
    }

}
