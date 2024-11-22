package com.codeonmars.propertiesms.model.property.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyFilter {
    private String owner;
    private Boolean forSale;
    private Boolean forRent;
    private Boolean rented;
    private Boolean sold;
    private SizeRange sizeRange;
    private String country;
    private String city;
    private Set<String> zones;
    private String typology;
    private Boolean furnished;
    private PriceRange priceRange;
    private YearRange yearRange;
}
