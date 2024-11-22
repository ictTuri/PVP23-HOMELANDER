package com.codeonmars.propertiesms.repository;

import com.codeonmars.propertiesms.model.location.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Long>{
    List<CountryEntity> findAllByApprovedOrderByName(boolean approved);

    boolean existsByName(String name);
}
