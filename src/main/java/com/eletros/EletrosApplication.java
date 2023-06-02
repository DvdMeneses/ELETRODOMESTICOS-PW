package com.eletros;

import com.eletros.Model.Usuario;
import com.eletros.Repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class EletrosApplication  implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(EletrosApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(UsuarioRepository usuarioRepository, PasswordEncoder encoder) {

        return args -> {
            usuarioRepository.deleteAll();
            List<Usuario> users = Arrays.asList(
                    new Usuario(null, "João", encoder.encode("admin"), true),
                    new Usuario(null, "Maria", encoder.encode("user1"),false),
                    new Usuario(null, "Pedro", encoder.encode("user2"),false)
            );


            for (var e : users) {
                System.out.println(e);
            }
            usuarioRepository.saveAll(users);
        };
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Register resource handler for images
        registry.addResourceHandler("static/images/img-uploads/**").addResourceLocations("/WEB-INF/images/");
    }

}
