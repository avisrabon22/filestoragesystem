package com.avijit.filestoragesystem.Service.FileStorageService;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class FileStorageStrategyImpl implements FileStorageStrategy{
    @Override
    public String storeFile(MultipartFile file) throws IOException {
        return "";
    }

    @Override
    public byte[] retrieveFile(String filePath) throws IOException {
        return new byte[0];
    }
}
