package com.codeonmars.propertiesms.model.location.dto;

import lombok.Data;

import java.util.List;

@Data
public class CityRequestDto {
    private String name;
    private boolean isCapitol;
    private List<AreaDto> areas;
}
