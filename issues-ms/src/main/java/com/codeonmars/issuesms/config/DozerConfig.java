package com.codeonmars.issuesms.config;

import com.codeonmars.issuesms.model.issues.IssuesEntity;
import com.codeonmars.issuesms.model.issues.dto.IssueRequest;
import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.github.dozermapper.core.DozerBeanMapperBuilder.create;
import static com.github.dozermapper.core.loader.api.TypeMappingOptions.mapNull;
import static com.github.dozermapper.core.loader.api.TypeMappingOptions.oneWay;

@Configuration
public class DozerConfig {

    @Bean
    public Mapper beanMapper() {
        return create().withMappingBuilders(new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(
                        type(IssueRequest.class),
                        type(IssuesEntity.class),
                        oneWay(),
                        mapNull(false));
            }
        }).build();
    }

}
