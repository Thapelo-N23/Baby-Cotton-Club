package za.ac.cput.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors
                        .configurationSource(request -> {
                            var config = new org.springframework.web.cors.CorsConfiguration();
                            config.setAllowedOrigins(java.util.List.of("http://localhost:3000", "http://localhost:3001"));
                            config.setAllowedMethods(java.util.List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
                            config.setAllowedHeaders(java.util.List.of("*"));
                            config.setAllowCredentials(true);
                            config.setMaxAge(3600L);
                            return config;
                        })
                )
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // admin-only actions
                        .requestMatchers(HttpMethod.POST, "/api/products/create").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/products/update/**").hasRole("ADMIN")

                        // customer-only actions
                        .requestMatchers(HttpMethod.POST, "/api/review/**").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.POST, "/api/cart/**").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.POST, "/api/cartitem/**").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.POST, "/api/order/create").hasRole("CUSTOMER")

                        // public read endpoints
                        .requestMatchers(HttpMethod.GET, "/api/products/**", "/api/product/**", "/api/category/**").permitAll()

                        // allow admin and customer login and customer registration endpoints so users can obtain tokens
                                .requestMatchers("/api/admin/login").permitAll()
                                .requestMatchers("/api/customer/login").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/customer/create").permitAll()


                        // protected customer endpoints (updated) - allow ADMIN as well for dashboard access
                        .requestMatchers("/api/payment/**").hasAnyRole("CUSTOMER", "ADMIN")
                        .requestMatchers("/api/customerorder/**").hasAnyRole("CUSTOMER", "ADMIN")
                        .requestMatchers("/api/order/**").hasAnyRole("CUSTOMER", "ADMIN")
                        // orderline endpoints should be accessible to customers and admins
                        .requestMatchers("/api/orderline/**").hasAnyRole("CUSTOMER", "ADMIN")
                        // address endpoints should remain accessible only to authenticated customers (addresses are customer-owned)
                        .requestMatchers("/address/**").hasRole("CUSTOMER")

                        // keep other endpoints permissive for now (you can tighten later)
                        .requestMatchers(
                                "/api/shipment/**",
                                "/api/supplier/**"
                        ).permitAll()

                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
