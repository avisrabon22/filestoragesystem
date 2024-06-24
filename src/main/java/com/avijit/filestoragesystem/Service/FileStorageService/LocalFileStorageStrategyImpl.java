package com.avijit.filestoragesystem.Service.FileStorageService;

import com.avijit.filestoragesystem.DTO.FileStorageDto.GetFileResponseDto;
import com.avijit.filestoragesystem.Model.FileInfo;
import com.avijit.filestoragesystem.Repo.FileStorageRepo;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class LocalFileStorageStrategyImpl implements FileStorageStrategyInterface {
    private final Path storageLocation;

    public LocalFileStorageStrategyImpl(FileStorageRepo fileStorageRepo) {
        String root = System.getProperty("user.dir");
        this.storageLocation = Paths.get(root,"Files");
    }

    @Override
    public String storeFile(MultipartFile file) throws IOException {
        if (!Files.exists(storageLocation)) {
            Files.createDirectories(storageLocation);
        }

        // Store the file
        Path destinationPath = storageLocation.resolve(file.getOriginalFilename());
        Files.copy(file.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

        return destinationPath.toString();
    }

    @Override
    public GetFileResponseDto retrieveFile(String filename) throws IOException {
        GetFileResponseDto getFileResponseDto = new GetFileResponseDto();

        Path filePath = Paths.get(storageLocation.toString(), filename);
        Resource resource = new UrlResource(filePath.toUri());
        if (resource.exists()) {
            getFileResponseDto.setContentType(Files.probeContentType(filePath));
            getFileResponseDto.setResource(resource);
            return getFileResponseDto;
        } else {
            throw new RuntimeException("File not found " + filename);
        }
    }
}
