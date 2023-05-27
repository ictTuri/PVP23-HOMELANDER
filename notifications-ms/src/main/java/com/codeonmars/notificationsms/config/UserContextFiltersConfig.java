package com.codeonmars.notificationsms.config;

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

    public static class AllowApiDocsExtractorFilter extends RequestingUserExtractor {

        protected boolean shouldContinue(HttpServletRequest request) {
            boolean original = super.shouldContinue(request);
            String requestURI = request.getRequestURI();
            Predicate<String> apiDocs = Pattern.compile("/notifications/api-docs").asPredicate();
            Predicate<String> apiSearch = Pattern.compile("/notifications/user").asPredicate();
            return original || apiDocs.test(requestURI) || apiSearch.test(requestURI);
        }
    }

    @Bean
    public RequestingUserExtractor credentialCheck() {
        return new AllowApiDocsExtractorFilter();
    }

    @Bean
    public UserContextFromUserMicroserviceFilter userContextFilter(@Autowired UserContextRetriever userContextRetriever) {
        return new UserContextFromUserMicroserviceFilter(userContextRetriever);
    }

    @Bean
    public AuthorizationChecks authorizationChecks() {
        return new AuthorizationChecks();
    }
}
