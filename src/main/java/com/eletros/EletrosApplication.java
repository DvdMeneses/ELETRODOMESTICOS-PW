package com.eletros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class EletrosApplication  implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(EletrosApplication.class, args);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Register resource handler for images
        registry.addResourceHandler("static/images/img-uploads/**").addResourceLocations("/WEB-INF/images/");
    }

}
