package com.codeonmars.propertiesms.model.property.projection;

import org.springframework.beans.factory.annotation.Value;

public interface PropertyOwnerTenantProjection {
    @Value("#{target.description}")
    String getDescription();
    @Value("#{target.ownerUsername}")
    String getOwnerUsername();
    @Value("#{target.ownerEmail}")
    String getOwnerEmail();
    @Value("#{target.tenantUsername}")
    String getTenantUsername();
    @Value("#{target.tenantEmail}")
    String getTenantEmail();

}
