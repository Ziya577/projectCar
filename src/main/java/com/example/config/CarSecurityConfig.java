package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class CarSecurityConfig {
    private final PasswordEncoder passwordEncoder;

    public CarSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails ziya = User.builder()
                .username("ziya")
                .password(passwordEncoder.encode("ziya577"))
                .roles("EMPLOYEE")
                .build();


        UserDetails kenan = User.builder()
                .username("kenan")
                .password(passwordEncoder.encode("kenan776"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(ziya, kenan);
    }

    @Bean
    SecurityFilterChain filterchain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/car/**")
                        .hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/car/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/car/create").hasRole("ADMIN"));


        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}
