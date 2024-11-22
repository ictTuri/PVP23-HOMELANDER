package com.codeonmars.usersms.model.user.dto;

import com.codeonmars.commonsms.dto.FileDto;
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
    private String firstName;
    private String lastName;
    private String phone;
    private boolean superuser;
    private String email;
    private String profilePicUUID;
    private String country;
    private String city;
    private boolean canAddProperty;
    private boolean canAddTenant;
    private Set<Long> propertyOwned = new HashSet<>();
    private Set<Long> propertyRented = new HashSet<>();
    private FileDto profileImage;
}
