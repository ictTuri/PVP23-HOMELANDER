package com.codeonmars.propertiesms.model.location;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "countries", schema = "properties_ms")
@EntityListeners(AuditingEntityListener.class)
public class CountryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "currency")
    private String currency;
    @Column(name = "abbreviation")
    private String abbreviation;
    @Column(name = "approved")
    private boolean approved;
    @OneToMany(mappedBy = "country", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<CityEntity> cities = new ArrayList<>();
    @CreatedDate
    @Column(name = "creation_date")
    private Instant creationDate;
    @UpdateTimestamp
    @Column(name = "last_updated_date")
    private Instant lastUpdatedDate;
}
