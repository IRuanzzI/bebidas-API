package com.lana.vitor.servico_bebdidas.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000", "https://bebidas-api.onrender.com", "http://localhost:8080", "https://front-blend.vercel.app") // URL do frontend
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}
