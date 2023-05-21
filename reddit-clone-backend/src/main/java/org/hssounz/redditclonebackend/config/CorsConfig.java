package org.hssounz.redditclonebackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200", "http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("Origin", "Access-Control-Allow-Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
                        "Origin, Accept", "X-Requested-With", "Access-Control-Request-Method", "Access-Control-Request-Headers")
                .exposedHeaders("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
                        "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename")
                .allowCredentials(true)
                .maxAge(3600); // Set an appropriate max age if needed
    }
}