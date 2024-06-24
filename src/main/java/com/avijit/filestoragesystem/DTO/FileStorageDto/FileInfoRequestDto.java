package com.avijit.filestoragesystem.DTO.FileStorageDto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
public class FileInfoRequestDto {
    private MultipartFile file;
    private String type;

}
