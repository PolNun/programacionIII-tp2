package org.ejemplo.config;

import org.ejemplo.repositories.ProductRepository;
import org.ejemplo.repositories.UserRepository;
import org.ejemplo.validations.ProductValidator;
import org.ejemplo.validations.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConf implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Bean
    UserValidator userValidator() {
        return new UserValidator(userRepository);
    }

    @Bean
    ProductValidator productValidator() {
        return new ProductValidator(productRepository);
    }
}