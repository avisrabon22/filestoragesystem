package com.avijit.filestoragesystem.Service.FileStorageService;

import com.avijit.filestoragesystem.DTO.FileStorageDto.GetFileResponseDto;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class FileStorageFactory {

    private final FileStorageStrategyInterface localFileStorageStrategyImpl;

    public FileStorageFactory(LocalFileStorageStrategyImpl localFileStorageStrategyImpl) {
        this.localFileStorageStrategyImpl = localFileStorageStrategyImpl;
    }

    public String setFileStorageStrategy(MultipartFile file, String type) throws IOException {
        if ("local".equalsIgnoreCase(type)) {
            return localFileStorageStrategyImpl.storeFile(file);
        }
        throw new IllegalArgumentException("Unknown storage type");
    }

    public GetFileResponseDto getFileStorageStrategy(String fileName) throws IOException {
        return localFileStorageStrategyImpl.retrieveFile(fileName);
    }
}
