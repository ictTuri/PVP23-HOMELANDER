package com.codeonmars.issuesms.remote;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(value = "properties-ms")
public interface PropertiesApi {


    @GetMapping("/properties/internal/property-owner-tenant-data")
    Optional<SimplePropertyData> getPropertyOwnerTenant(@RequestParam(name = "id") Long id,
                                                        @RequestParam(name = "tenant") String tenant);

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class SimplePropertyData {
        private String description;
        private String ownerEmail;
        private String ownerUsername;
        private String tenantEmail;
        private String tenantUsername;
    }
}
