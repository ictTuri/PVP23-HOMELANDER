package com.codeonmars.propertiesms.service;

import com.codeonmars.propertiesms.model.property.PropertiesEntity;
import com.codeonmars.propertiesms.model.property.projection.PropertyOwnerTenantProjection;
import com.codeonmars.propertiesms.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PropertyGeneralService {

    private final PropertyRepository propertyRepository;
    private static final String OWN = "own";
    private static final String RENTS = "rents";

    public PropertyGeneralService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public Optional<PropertyOwnerTenantProjection> getPropertyOwnerTenant(Long id, String tenant) {
        return propertyRepository.getPropertyOwnerTenant(id, tenant);
    }

    public Map<String, Set<Long>> getUsersPropertiesId(String username, String email) {
        Map<String, Set<Long>> toReturn = new HashMap<>();
        Set<PropertiesEntity> properties = propertyRepository.getUsersProperties(username, email);
        toReturn.put(OWN, properties.stream()
                .filter(prop -> prop.getOwner().getUsername().equals(username) && prop.getOwner().getEmail().equals(email))
                .map(PropertiesEntity::getId)
                .collect(Collectors.toSet()));
        toReturn.put(RENTS, properties.stream()
                .filter(prop -> prop.getTenant().getUsername().equals(username) && prop.getTenant().getEmail().equals(email))
                .map(PropertiesEntity::getId)
                .collect(Collectors.toSet()));
        return toReturn;
    }
}
