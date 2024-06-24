package com.avijit.filestoragesystem.Service.FileStorageService;


import com.avijit.filestoragesystem.Repo.FileStorageRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileStorageService {

    private final FileStorageRepo fileStorageRepo;
    private final FileStorageFactory fileStorageStrategyFactory;


    public FileStorageService(FileStorageRepo fileStorageRepo, FileStorageFactory fileStorageStrategyFactory) {
        this.fileStorageRepo = fileStorageRepo;
        this.fileStorageStrategyFactory = fileStorageStrategyFactory;
    }

    public String storeFile(MultipartFile file, String type) throws IOException {
        FileStorageStrategyInterface fileStorageStrategy = fileStorageStrategyFactory.getFileStorageStrategy(type);
        return fileStorageStrategy.storeFile(file,type);
    }

}
