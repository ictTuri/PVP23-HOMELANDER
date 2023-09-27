package com.codeonmars.propertiesms.model.property.dto;

import com.codeonmars.commonsms.dto.FileDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalAttributesDto {
    private Long id;
    private Boolean furnished;
    private String typology;
    private String type;
    private Set<String> appliances;
    private FileDto image;
    private Set<FileDto> images;
}
