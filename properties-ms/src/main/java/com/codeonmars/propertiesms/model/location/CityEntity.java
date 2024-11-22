package com.codeonmars.propertiesms.model.location;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "cities", schema = "properties_ms")
@EntityListeners(AuditingEntityListener.class)
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "approved")
    private boolean approved;
    @Column(name = "is_capitol")
    private boolean isCapitol;
    @ManyToOne
    @JoinColumn(name="country_id")
    private CountryEntity country;
    @OneToMany(mappedBy = "city", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<AreaEntity> areas = new ArrayList<>();
    @CreatedDate
    @Column(name = "creation_date")
    private Instant creationDate;
    @UpdateTimestamp
    @Column(name = "last_updated_date")
    private Instant lastUpdatedDate;
}
