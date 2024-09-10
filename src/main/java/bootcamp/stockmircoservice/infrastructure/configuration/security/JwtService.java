package bootcamp.stockmircoservice.infrastructure.configuration.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;


import java.security.Key;

public class JwtService {
    @Value("${app-security-key}")
    private String secretKey;

    public String extractUsername(String jwt) {
        return extractAllClaims(jwt).getSubject();
    }
    public String extractRole(String jwt){
        return extractAllClaims(jwt).get("Role").toString();
    }
    private Claims extractAllClaims(String jwt) {
        return Jwts.parserBuilder().setSigningKey(generateKey()).build().parseClaimsJws(jwt).getBody();
    }
    private Key generateKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
}
