package com.VivianneApi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf().disable()
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers(HttpMethod.POST, "/admins/members", "/admin/members/")
                    .permitAll()
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/mypages/members/**").hasAnyRole("USER", "ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/mypages/members/**").hasAnyRole("USER", "ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/admin/members/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
            )
            .httpBasic(org.springframework.security.config.Customizer.withDefaults());
    return http.build();
}

@Bean
    public org.springframework.security.crypto.password.PasswordEncoder passwordEncoder() {
return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
}
}
