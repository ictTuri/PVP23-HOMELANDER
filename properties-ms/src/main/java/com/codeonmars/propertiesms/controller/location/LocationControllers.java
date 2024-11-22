package com.codeonmars.propertiesms.controller.location;

import com.codeonmars.commonsms.aspects.IsSuperUser;
import com.codeonmars.propertiesms.model.location.dto.AreaDto;
import com.codeonmars.propertiesms.model.location.dto.CityDto;
import com.codeonmars.propertiesms.model.location.dto.CountryDto;
import com.codeonmars.propertiesms.model.location.dto.CountryRequestDto;
import com.codeonmars.propertiesms.service.location.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/properties/search/locations")
public class LocationControllers {

    private final LocationService locationService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CountryDto> getCountries() {
        return locationService.getCountries();
    }

    @GetMapping("/country/{country-id}/cities")
    @ResponseStatus(HttpStatus.OK)
    public List<CityDto> getCitiesByCountryId(@PathVariable(name = "country-id") Long countryId) {
        return locationService.getCitiesByCountryId(countryId);
    }

    @GetMapping("/city/{city-id}/areas")
    @ResponseStatus(HttpStatus.OK)
    public List<AreaDto> getAreasByCityId(@PathVariable(name = "city-id") Long cityId) {
        return locationService.getAreasByCityId(cityId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @IsSuperUser
    public Long createNewGeoLocations(@RequestBody CountryRequestDto request) {
        return locationService.createNewGeoLocations(request);
    }

    public LocationControllers(LocationService locationService) {
        this.locationService = locationService;
    }
}
