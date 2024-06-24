package com.avijit.filestoragesystem.Service.FileStorageService;

import com.avijit.filestoragesystem.Repo.FileStorageRepo;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class LocalFileStorageStrategyImpl implements FileStorageStrategyInterface {
    private final FileStorageRepo fileStorageRepo;
    private final Path storageLocation;

    public LocalFileStorageStrategyImpl(FileStorageRepo fileStorageRepo) {
        String root = System.getProperty("user.dir");
        this.storageLocation = Paths.get(root,"Files");
        this.fileStorageRepo = fileStorageRepo;

    }

    @Override
    public String storeFile(MultipartFile file,String type) throws IOException {
        if (!Files.exists(storageLocation)) {
            Files.createDirectories(storageLocation);
        }

        // Store the file
        Path destinationPath = storageLocation.resolve(file.getOriginalFilename());
        Files.copy(file.getInputStream(), destinationPath);

        return destinationPath.toString();
    }

    @Override
    public byte[] retrieveFile(String filePath) throws IOException {
        return new byte[0];
    }
}
