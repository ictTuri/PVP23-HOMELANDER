package com.codeonmars.propertiesms.model.property.requests;

import com.codeonmars.propertiesms.model.property.dto.AdditionalAttributesDto;
import com.codeonmars.propertiesms.model.property.dto.PropertyAddressDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyRequest {
    private String description;
    private String longDescription;
    private Double size;
    private String unit;
    private Double price;
    private Integer year;
    private Boolean forRent;
    private Boolean forSale;
    private PropertyAddressDto propertyAddress;
    private AdditionalAttributesUpdateRequest additionalAttributes;
}
