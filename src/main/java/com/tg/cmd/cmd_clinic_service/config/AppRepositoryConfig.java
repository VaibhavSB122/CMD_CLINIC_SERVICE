package com.tg.cmd.cmd_clinic_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class AppRepositoryConfig implements RepositoryRestConfigurer {

//     Configure repository REST configuration
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        // Set whether to expose repository methods by default
        config.setExposeRepositoryMethodsByDefault(false);
    }
}