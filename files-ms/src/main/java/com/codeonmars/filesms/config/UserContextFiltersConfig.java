package com.codeonmars.filesms.config;

import com.codeonmars.commonsms.aspects.AuthorizationChecks;
import com.codeonmars.commonsms.filter.RequestingUserExtractor;
import com.codeonmars.commonsms.filter.UserContextFromUserMicroserviceFilter;
import com.codeonmars.commonsms.security.UserContextRetriever;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@Configuration
public class UserContextFiltersConfig {

    @Autowired
    private UserContextRetriever userContextRetriever;

    public static class AllowApiDocsExtractorFilter extends RequestingUserExtractor {

        protected boolean shouldContinue(HttpServletRequest request) {
            boolean original = super.shouldContinue(request);
            String requestURI = request.getRequestURI();
            Predicate<String> apiDocs = Pattern.compile("/files/api-docs").asPredicate();
            Predicate<String> apiFiles = Pattern.compile("/files/internal/property/*").asPredicate();
            return original || apiDocs.test(requestURI) || apiFiles.test(requestURI);
        }
    }

    @Bean
    public RequestingUserExtractor credentialCheck() {
        return new AllowApiDocsExtractorFilter();
    }

    @Bean
    public UserContextFromUserMicroserviceFilter userContextFilter() {
        return new UserContextFromUserMicroserviceFilter(userContextRetriever);
    }

    @Bean
    public AuthorizationChecks authorizationChecks() {
        return new AuthorizationChecks();
    }
}
