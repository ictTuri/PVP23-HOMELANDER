package com.codeonmars.propertiesms.repository;

import com.codeonmars.propertiesms.model.property.PropertiesEntity;
import com.codeonmars.propertiesms.model.property.projection.PropertyOwnerTenantProjection;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
@NonNullApi
public interface PropertyRepository extends JpaRepository<PropertiesEntity, Long>, JpaSpecificationExecutor<PropertiesEntity> {

    Page<PropertiesEntity> findAll(@Nullable Specification<PropertiesEntity> predicates, Pageable pageable);

    Optional<PropertiesEntity> findByIdAndOwner_Email(Long propertyId, String loggedUserEmail);

    @Query(value = """
            SELECT  p.description as description,
                    o.username    as ownerUsername,
                    o.email       as ownerEmail,
                    t.username    as tenantUsername,
                    t.email       as tenantEmail
                    FROM PropertiesEntity p
                    INNER JOIN OwnerEntity o ON p.owner.id = o.id
                    INNER JOIN TenantEntity t on p.tenant.id = t.id
                    WHERE p.id = :id AND p.tenant.username = :tenant""")
    Optional<PropertyOwnerTenantProjection> getPropertyOwnerTenant(@Param("id") Long id, @Param("tenant") String tenant);

    @Query(value = """
            SELECT p from PropertiesEntity p
            INNER JOIN OwnerEntity o ON p.owner.id = o.id
            INNER JOIN TenantEntity t on p.tenant.id = t.id
            WHERE (o.username = :username AND o.email = :email)
            OR (t.username = :username AND t.email = :email)""")
    Set<PropertiesEntity> getUsersProperties(String username, String email);
}
