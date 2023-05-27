package com.codeonmars.propertiesms.controller;

import com.codeonmars.propertiesms.model.property.projection.PropertyOwnerTenantProjection;
import com.codeonmars.propertiesms.service.PropertyGeneralService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/properties/internal")
public class InternalController {

    private final PropertyGeneralService generalService;

    public InternalController(PropertyGeneralService generalService) {
        this.generalService = generalService;
    }

    @GetMapping("/property-owner-tenant-data")
    public Optional<PropertyOwnerTenantProjection> getPropertyOwnerTenant(@RequestParam(name = "id") Long id,
                                                                          @RequestParam(name = "tenant") String tenant) {
        return generalService.getPropertyOwnerTenant(id, tenant);
    }

    @GetMapping("/user-properties")
    public Map<String, Set<Long>> getUsersProperties(@RequestParam(name = "username") String username,
                                                     @RequestParam(name = "email") String email) {
        return generalService.getUsersPropertiesId(username, email);
    }
}
