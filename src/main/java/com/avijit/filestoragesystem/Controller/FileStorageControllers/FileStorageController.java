package com.avijit.filestoragesystem.Controller.FileStorageControllers;

import com.avijit.filestoragesystem.DTO.FileStorageDto.FileInfoRequestDto;
import com.avijit.filestoragesystem.DTO.FileStorageDto.FileInfoResponseDto;
import com.avijit.filestoragesystem.DTO.FileStorageDto.GetFileResponseDto;
import com.avijit.filestoragesystem.Service.FileStorageService.FileStorageService;
import org.apache.hc.client5.http.entity.mime.MultipartPart;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class FileStorageController {
   private final FileStorageService fileStorageService;

    public FileStorageController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

//    Upload file to file storage and save file info to database ***************
    @PostMapping("/upload_file")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> uploadFile(@RequestBody MultipartFile file,  String type) throws IOException {
        FileInfoRequestDto fileInfoRequestDto = new FileInfoRequestDto();
        fileInfoRequestDto.setFile(file);
        fileInfoRequestDto.setType(type);

        try {
            FileInfoResponseDto fileInfoResponseDto =fileStorageService.storeFile(fileInfoRequestDto);
            return ResponseEntity.ok(fileInfoResponseDto);
        } catch (IOException e) {
           return ResponseEntity.badRequest().body("File upload failed");
        }
    }

//    Download file from file storage ***************
    @GetMapping("/download_file/{fileName:.+}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> downloadFile(@PathVariable("filename") String filename) throws IOException {
        try {
            GetFileResponseDto getFileResponseDto =fileStorageService.retrieveFile(filename);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(getFileResponseDto.getContentType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + getFileResponseDto.getResource().getFilename() + "\"")
                    .body(getFileResponseDto.getResource());
        }
        catch (IOException e) {
            return ResponseEntity.badRequest().body("File download failed");
        }
    }

}
