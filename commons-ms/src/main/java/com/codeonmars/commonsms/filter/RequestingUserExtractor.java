package com.codeonmars.commonsms.filter;

import com.codeonmars.commonsms.utils.Constants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

public class RequestingUserExtractor extends OncePerRequestFilter {

    @Value("${app.header.secret}")
    private String headerSecret;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(!this.isOption(request) && !shouldContinue(request)){
            String credential = request.getHeader(Constants.X_HL_CONTEXT_CREDENTIAL);
            String secret = request.getHeader(Constants.X_HL_SECRET);
              if(credential == null || !Objects.equals(secret, headerSecret)){
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "User not authenticated");
            } else {
                filterChain.doFilter(request, response);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private Boolean isOption(HttpServletRequest httpServletRequest){ return httpServletRequest.getMethod().equals("OPTIONS"); }

    protected boolean shouldContinue(HttpServletRequest httpRequest) { return false; }
}
