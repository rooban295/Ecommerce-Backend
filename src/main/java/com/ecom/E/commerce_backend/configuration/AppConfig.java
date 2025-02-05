package com.ecom.E.commerce_backend.configuration;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request->
                        request.requestMatchers("api/admin/**").hasAnyRole("ADMIN")
                                .requestMatchers("api/cart/**").authenticated()
                                .anyRequest().permitAll())
                .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
                .cors(cros->cros.configurationSource(corsConfigurationSource()))
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration cfg = new CorsConfiguration();
                cfg.setAllowedOrigins(List.of("http://localhost:4500","https://ecommerce-rho-khaki.vercel.app/"));
                cfg.setAllowCredentials(true);
                cfg.addAllowedHeader("*");
                cfg.addAllowedMethod("*");
                cfg.addExposedHeader("Authorization");

                return cfg;
            }
        };
    }

    @Bean   //its convert plain password into EncryptPassword
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
