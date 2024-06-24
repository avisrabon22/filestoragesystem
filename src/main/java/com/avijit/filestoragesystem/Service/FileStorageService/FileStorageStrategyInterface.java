package com.avijit.filestoragesystem.Service.FileStorageService;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageStrategyInterface {
    String storeFile(MultipartFile file,String type) throws IOException;
    byte[] retrieveFile(String filePath) throws IOException;
}
