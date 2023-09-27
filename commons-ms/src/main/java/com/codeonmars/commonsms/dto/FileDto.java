package com.codeonmars.commonsms.dto;

import lombok.Data;

@Data
public class FileDto {
    private Long id;
    private String fileName;
    private String fileContent;
    private String uuid;
}
