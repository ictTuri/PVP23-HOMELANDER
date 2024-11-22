package com.codeonmars.propertiesms.model.location.dto;

import lombok.Data;

import java.util.List;

@Data
public class CountryRequestDto {
    private String name;
    private String currency;
    private String abbreviation;
    private List<CityRequestDto> cities;
}
