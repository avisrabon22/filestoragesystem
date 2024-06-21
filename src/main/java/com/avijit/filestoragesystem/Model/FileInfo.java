package com.avijit.filestoragesystem.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
public class FileInfo extends BaseModel {
    @Column(nullable = false)
    private String filename;

    @Column(nullable = false)
    private String filePath;

    private String contentType;
    private Long size;

    @Column(nullable = false, updatable = false)
    private LocalDateTime uploadTimestamp;
}
