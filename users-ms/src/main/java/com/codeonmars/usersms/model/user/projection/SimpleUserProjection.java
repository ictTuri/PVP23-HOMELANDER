package com.codeonmars.usersms.model.user.projection;

import org.springframework.beans.factory.annotation.Value;

public interface SimpleUserProjection {

    @Value("#{target.username}")
    String getUsername();
    @Value("#{target.email}")
    String getEmail();
}
