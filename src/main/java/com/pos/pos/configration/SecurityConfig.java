package com.pos.pos.configration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){

        return http
                .sessionManagement(management ->
                        management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests( authorize ->
                        authorize.requestMatchers("/api/**").authenticated()
                                .requestMatchers("/api/super-admin/**")
                                .hasRole("ADMIN").anyRequest().permitAll())
                .addFilterBefore(new jwtValidator(), BasicAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .cors( cors ->
                        cors.configurationSource(corsConfigrationSource())).build();
    }

    private CorsConfigurationSource corsConfigrationSource() {
        return null;
    }
}
