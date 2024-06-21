package com.avijit.filestoragesystem.DTO.FileStorageDto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class FileInfoDto {
    private Long id;
    private String filename;
    private String filePath;
    private String contentType;
    private Long size;
    private LocalDateTime uploadTimestamp;
}
