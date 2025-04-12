package com.miltonneto.todosimples.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
    
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
        .allowedOrigins("*") // ou coloque sua origem ex: http://localhost:5500
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") 
        .allowedHeaders("*");
}
}