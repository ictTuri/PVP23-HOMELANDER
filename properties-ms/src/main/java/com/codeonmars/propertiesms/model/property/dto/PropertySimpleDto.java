package com.codeonmars.propertiesms.model.property.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertySimpleDto {
    private Long id;
    private String description;
    private String longDescription;
    private Double size;
    private String unit;
    private Double price;
    private Integer year;
    private PropertyAddressDto propertyAddress;
    private AdditionalAttributesDto additionalAttributes;
}
