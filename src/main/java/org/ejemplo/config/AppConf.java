package org.ejemplo.config;

import org.ejemplo.repository.UsuarioRepository;
import org.ejemplo.validations.UserValidations;
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
    private UsuarioRepository usuarioRepository;

    @Bean
    public UserValidations userValidations() {
        return new UserValidations(usuarioRepository);
    }
}