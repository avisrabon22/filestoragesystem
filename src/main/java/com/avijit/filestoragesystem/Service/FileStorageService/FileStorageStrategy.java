package com.avijit.filestoragesystem.Service.FileStorageService;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageStrategy {
    String storeFile(MultipartFile file) throws IOException;
    byte[] retrieveFile(String filePath) throws IOException;
}
