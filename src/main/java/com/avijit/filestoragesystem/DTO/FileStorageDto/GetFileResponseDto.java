package com.avijit.filestoragesystem.DTO.FileStorageDto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.Resource;

@Getter
@Setter
public class GetFileResponseDto {
    private Resource resource;
    private String contentType;
}
