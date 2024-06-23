package com.avijit.filestoragesystem.Service.FileStorageService;

import org.springframework.stereotype.Component;

@Component
public class FileStorageFactory {

    private final LocalFileStorageStrategyImpl localFileStorageStrategyImpl;

    public FileStorageFactory(LocalFileStorageStrategyImpl localFileStorageStrategyImpl) {
        this.localFileStorageStrategyImpl = localFileStorageStrategyImpl;
    }

    public FileStorageStrategyInterface getFileStorageStrategy(String type) {
        if ("local".equalsIgnoreCase(type)) {
            return localFileStorageStrategyImpl;
        }
        throw new IllegalArgumentException("Unknown storage type");
    }
}
