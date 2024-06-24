package com.avijit.filestoragesystem.Service.FileStorageService;


import com.avijit.filestoragesystem.DTO.FileStorageDto.FileInfoRequestDto;
import com.avijit.filestoragesystem.DTO.FileStorageDto.FileInfoResponseDto;
import com.avijit.filestoragesystem.DTO.FileStorageDto.GetFileResponseDto;
import com.avijit.filestoragesystem.Model.FileInfo;
import com.avijit.filestoragesystem.Repo.FileStorageRepo;
import org.springframework.core.io.Resource;
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

//    Store file to file storage and save file info to database ***************
    public FileInfoResponseDto storeFile(FileInfoRequestDto fileInfoRequestDto) throws IOException {
        MultipartFile file = fileInfoRequestDto.getFile();
        String type = fileInfoRequestDto.getType();
        String filePath = fileStorageStrategyFactory.setFileStorageStrategy(file, type);

        FileInfo existingFileInfo = fileStorageRepo.findByFilename(file.getOriginalFilename());

//        Save file info to database as mata data ***************
        FileInfo fileInfo = (existingFileInfo!=null)?existingFileInfo:new FileInfo();
        fileInfo.setFilename(file.getOriginalFilename());
        fileInfo.setFilePath(filePath);
        fileInfo.setSize(file.getSize());
        fileInfo.setContentType(file.getContentType());
        fileStorageRepo.save(fileInfo);

//        Return file info as response ***************
        FileInfoResponseDto fileInfoResponseDto = new FileInfoResponseDto();
        fileInfoResponseDto.setFileName(file.getOriginalFilename());
        return fileInfoResponseDto;
    }

//    Retrieve file from file storage ***************
    public GetFileResponseDto retrieveFile(String filename) throws IOException {
        return fileStorageStrategyFactory.getFileStorageStrategy(filename);
    }
}
