package com.codeonmars.usersms.model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullUserContextDto {
    private String username;
    private String email;
    private String country;
    private String city;
    private Set<Long> propertyOwned = new HashSet<>();
    private Set<Long> propertyRented = new HashSet<>();
}
