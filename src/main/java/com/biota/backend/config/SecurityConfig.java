package com.biota.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.Customizer;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desactivar CSRF (útil para desarrollo/API)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").permitAll() // Permitir todo
                        .anyRequest().permitAll()
                )
                .httpBasic(withDefaults()) // Opcional: desactiva login visual
                .formLogin(form -> form.disable()); // Desactivar formulario de login
        return http.build();
    }
}