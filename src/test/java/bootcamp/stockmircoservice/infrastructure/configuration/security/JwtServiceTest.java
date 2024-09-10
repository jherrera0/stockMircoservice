package bootcamp.stockmircoservice.infrastructure.configuration.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtServiceTest {

    @InjectMocks
    private JwtService jwtService;

    @Mock
    private Claims claims;

    @Value("${app-security-key}")
    private String secretKey = "mysecretkeymysecretkeymysecretkeymysecretkey";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void extractUsername_InvalidJwt_ThrowsException() {
        String jwt = "invalidJwt";

        assertThrows(Exception.class, () -> jwtService.extractUsername(jwt));
    }

    @Test
    void extractRole_InvalidJwt_ThrowsException() {
        String jwt = "invalidJwt";

        assertThrows(Exception.class, () -> jwtService.extractRole(jwt));
    }

    @Test
    void generateKey_ReturnsValidKey() {
        Key key = jwtService.generateKey();

        assertNotNull(key);
        assertEquals(Keys.hmacShaKeyFor(secretKey.getBytes()).getAlgorithm(), key.getAlgorithm());
    }
}