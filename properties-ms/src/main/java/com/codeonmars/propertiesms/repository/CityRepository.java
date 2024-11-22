package com.codeonmars.propertiesms.repository;

import com.codeonmars.propertiesms.model.location.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {

    List<CityEntity> findAllByCountryIdOrderByName(Long countryId);
}
