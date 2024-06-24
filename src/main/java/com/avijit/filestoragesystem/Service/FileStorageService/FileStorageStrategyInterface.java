package com.avijit.filestoragesystem.Service.FileStorageService;

import com.avijit.filestoragesystem.DTO.FileStorageDto.GetFileResponseDto;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageStrategyInterface {
    String storeFile(MultipartFile file) throws IOException;
    GetFileResponseDto retrieveFile(String filePath) throws IOException;
}
