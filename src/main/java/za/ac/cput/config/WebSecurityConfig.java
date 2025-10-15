package za.ac.cput.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors().and()
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/api/customer/**",
                    "/api/admin/**",
                    "/api/product/**",
                    "/api/products/**",
                    "/api/category/**",
                    "/api/orderline/**",
                    "/api/cart/**",
                    "/api/cartitem/**",
                    "/address/**",
                    "/api/payment/**",
                    "/api/review/**",
                    "/api/shipment/**",
                    "/api/supplier/**",
                    "/api/customerorder/**",
                    "/api/order/**"
                ).permitAll()
                .anyRequest().authenticated()
            );
        return http.build();
    }
}
