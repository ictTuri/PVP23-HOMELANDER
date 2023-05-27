package com.codeonmars.usersms.config;

import com.codeonmars.usersms.model.user.UserEntity;
import com.codeonmars.usersms.model.user.dto.FullUserContextDto;
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
                                type(UserEntity.class),
                                type(FullUserContextDto.class),
                                oneWay(),
                                mapNull(true))
                                .fields("address.country", "country")
                                .fields("address.city", "city");
                    }
                })

                .build();
    }

}
