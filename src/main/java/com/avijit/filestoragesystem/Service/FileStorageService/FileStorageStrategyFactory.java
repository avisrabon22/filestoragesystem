package com.avijit.filestoragesystem.Service.FileStorageService;


import com.avijit.filestoragesystem.Service.FileStorageService.FileStorageStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileStorageStrategyFactory {

    private final FileStorageStrategyImpl fileStorageStrategyImpl;

    public FileStorageStrategyFactory(FileStorageStrategyImpl fileStorageStrategyImpl) {
        this.fileStorageStrategyImpl = fileStorageStrategyImpl;
    }

    public FileStorageStrategy getFileStorageStrategy(String type) {
        if ("local".equalsIgnoreCase(type)) {
            return fileStorageStrategyImpl;
        }
        throw new IllegalArgumentException("Unknown storage type");
    }
}
