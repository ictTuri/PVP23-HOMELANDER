package com.codeonmars.commonsms.filter;

import com.codeonmars.commonsms.security.UserContextHolder;
import com.codeonmars.commonsms.security.UserContextRetriever;
import com.codeonmars.commonsms.utils.Constants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

public class UserContextFromUserMicroserviceFilter extends OncePerRequestFilter {
    @Value("${app.header.secret}")
    private String headerSecret;
    private final UserContextRetriever userContextRetriever;

    public UserContextFromUserMicroserviceFilter(UserContextRetriever userContextRetriever) {
        this.userContextRetriever = userContextRetriever;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String credential = request.getHeader(Constants.X_HL_CONTEXT_CREDENTIAL);
        String secret = request.getHeader(Constants.X_HL_SECRET);
        if(credential != null && Objects.equals(secret, headerSecret)){
            userContextRetriever.retrieveUserContext(credential).ifPresent(UserContextHolder::setContext);
        }
        filterChain.doFilter(request, response);
        UserContextHolder.clearContext();
    }
}
