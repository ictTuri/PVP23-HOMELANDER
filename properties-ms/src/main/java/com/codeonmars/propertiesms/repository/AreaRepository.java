package com.codeonmars.propertiesms.repository;

import com.codeonmars.propertiesms.model.location.AreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaRepository extends JpaRepository<AreaEntity, Long> {
    List<AreaEntity> findAllByCityIdOrderByName(Long cityId);
}
