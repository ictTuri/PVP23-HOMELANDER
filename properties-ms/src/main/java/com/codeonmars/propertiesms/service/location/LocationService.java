package com.codeonmars.propertiesms.service.location;

import com.codeonmars.commonsms.exception.CanNotAddResources;
import com.codeonmars.propertiesms.model.location.CountryEntity;
import com.codeonmars.propertiesms.model.location.dto.AreaDto;
import com.codeonmars.propertiesms.model.location.dto.CityDto;
import com.codeonmars.propertiesms.model.location.dto.CountryDto;
import com.codeonmars.propertiesms.model.location.dto.CountryRequestDto;
import com.codeonmars.propertiesms.repository.AreaRepository;
import com.codeonmars.propertiesms.repository.CityRepository;
import com.codeonmars.propertiesms.repository.CountryRepository;
import com.github.dozermapper.core.Mapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class LocationService {

    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final AreaRepository areaRepository;
    private final Mapper dozer;

    public List<CountryDto> getCountries() {
        var toReturn = countryRepository.findAllByApprovedOrderByName(true);
        return toReturn.stream().map(country -> dozer.map(country, CountryDto.class)).toList();
    }

    public List<CityDto> getCitiesByCountryId(Long countryId) {
        var toReturn = cityRepository.findAllByCountryIdOrderByName(countryId);
        return toReturn.stream().map(country -> dozer.map(country, CityDto.class)).toList();
    }

    public List<AreaDto> getAreasByCityId(Long cityId) {
        var toReturn = areaRepository.findAllByCityIdOrderByName(cityId);
        return toReturn.stream().map(country -> dozer.map(country, AreaDto.class)).toList();
    }

    public LocationService(CountryRepository countryRepository, CityRepository cityRepository, AreaRepository areaRepository, Mapper dozer) {
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
        this.areaRepository = areaRepository;
        this.dozer = dozer;
    }

    public Long createNewGeoLocations(CountryRequestDto request) {
        var existCountryByName = countryRepository.existsByName(request.getName().trim());
        if (!existCountryByName) {
            var entity = dozer.map(request, CountryEntity.class);
            entity.getCities().forEach(city -> {
                city.setCountry(entity);
                city.getAreas().forEach(area -> area.setCity(city));
            });
            return countryRepository.save(entity).getId();
        } else {
            throw new CanNotAddResources("Country name already exists!");
        }

    }
}
