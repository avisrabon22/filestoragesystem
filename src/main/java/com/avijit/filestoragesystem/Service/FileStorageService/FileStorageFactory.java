package com.avijit.filestoragesystem.Service.FileStorageService;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class FileStorageFactory {

    private final FileStorageStrategyInterface localFileStorageStrategyImpl;

    public FileStorageFactory(LocalFileStorageStrategyImpl localFileStorageStrategyImpl) {
        this.localFileStorageStrategyImpl = localFileStorageStrategyImpl;
    }

    public String getFileStorageStrategy(MultipartFile file, String type) throws IOException {
        if ("local".equalsIgnoreCase(type)) {
            return localFileStorageStrategyImpl.storeFile(file,type);
        }
        throw new IllegalArgumentException("Unknown storage type");
    }
}
