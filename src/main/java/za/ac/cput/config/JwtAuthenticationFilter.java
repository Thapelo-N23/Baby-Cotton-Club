package za.ac.cput.config;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.Key;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private JwtKeyProvider jwtKeyProvider;

    private Key key;

    @PostConstruct
    public void init() {
        this.key = jwtKeyProvider.getKey();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            try {
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
                String username = claims.getSubject();
                if (username != null) {
                    List<SimpleGrantedAuthority> authorities = List.of();
                    Object rolesObj = claims.get("roles");
                    if (rolesObj instanceof java.util.Collection) {
                        authorities = ((java.util.Collection<?>) rolesObj).stream()
                                .map(Object::toString)
                                .map(role -> {
                                    if (role.startsWith("ROLE_")) return role;
                                    return "ROLE_" + role;
                                })
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toList());
                    } else if (rolesObj instanceof String) {
                        String role = (String) rolesObj;
                        if (!role.startsWith("ROLE_")) {
                            role = "ROLE_" + role;
                        }
                        authorities = List.of(new SimpleGrantedAuthority(role));
                    }

                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, authorities);
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);

                    // Log authenticated user and authorities for debugging
                    logger.info("JWT parsed for user '{}', assigned authorities: {}", username, authorities.stream().map(Object::toString).collect(Collectors.joining(",")));
                }
            } catch (JwtException | IllegalArgumentException e) {
                logger.warn("Invalid JWT token for request to {}: {}", request.getRequestURI(), e.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }
}
