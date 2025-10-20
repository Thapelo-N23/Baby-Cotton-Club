package za.ac.cput.config;

import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.WeakKeyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;

@Component
public class JwtKeyProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public Key getKey() {
        byte[] secretBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
        try {
            return Keys.hmacShaKeyFor(secretBytes);
        } catch (WeakKeyException e) {
            try {
                MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
                byte[] digest = sha256.digest(secretBytes);
                return Keys.hmacShaKeyFor(digest);
            } catch (Exception ex){
                return Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
            }
        }
    }
}

