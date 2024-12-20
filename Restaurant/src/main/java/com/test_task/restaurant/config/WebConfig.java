package com.test_task.restaurant.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Разрешаем все запросы
                .allowedOrigins("http://localhost:5173") // Указываем адрес фронтенда
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS"); // Разрешаем эти методы
    }
}
