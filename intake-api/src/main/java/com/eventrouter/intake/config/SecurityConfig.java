package com.eventrouter.intake.config;

import com.eventrouter.intake.security.WebhookSignatureFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final WebhookSignatureFilter webhookSignatureFilter;

    public SecurityConfig(WebhookSignatureFilter webhookSignatureFilter) {
        this.webhookSignatureFilter = webhookSignatureFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/webhooks/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(webhookSignatureFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}