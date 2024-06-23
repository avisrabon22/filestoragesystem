package com.avijit.filestoragesystem.Service.FileStorageService;


import com.avijit.filestoragesystem.Repo.FileStorageRepo;
import org.springframework.stereotype.Service;

@Service
public class FileStorageService {

    private final FileStorageRepo fileStorageRepo;
    private final FileStorageFactory fileStorageStrategyFactory;


    public FileStorageService(FileStorageRepo fileStorageRepo, FileStorageFactory fileStorageStrategyFactory) {
        this.fileStorageRepo = fileStorageRepo;
        this.fileStorageStrategyFactory = fileStorageStrategyFactory;
    }


}
