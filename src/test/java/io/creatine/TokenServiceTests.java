package io.creatine;

import io.creatine.user.adapter.properties.JwtTokenProperties;
import io.creatine.user.adapter.JwtTokenServiceAdapter;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TokenServiceTests {

    @Mock
    private JwtTokenProperties tokenProperties;

    private JwtTokenServiceAdapter jwtTokenService;

    private static final String SECRET_KEY = "test-secret-key-that-is-long-enough-for-hs256";
    private static final int EXPIRES_IN = 3_600_000; // 1 hour

    @BeforeEach
    void setUp() {
        // Configure mock behavior
        when(tokenProperties.getSecretKey()).thenReturn(SECRET_KEY);
        when(tokenProperties.getExpiresIn()).thenReturn((long) EXPIRES_IN);

        // Initialize the service with mocked properties
        jwtTokenService = new JwtTokenServiceAdapter(tokenProperties);
    }

    @Test
    void generateToken_ValidInput_ReturnsNonNullToken() {
        // Arrange
        String subject = "testUser";
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", "ADMIN");

        // Act
        String token = jwtTokenService.generateToken(subject, claims);

        // Assert
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void generateToken_NullClaims_ReturnsNonNullToken() {
        // Arrange
        String subject = "testUser";

        // Act
        String token = jwtTokenService.generateToken(subject, null);

        // Assert
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void generatedToken_CanBeDecoded() {
        // Arrange
        String subject = "testUser";
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", "ADMIN");

        // Act
        String token = jwtTokenService.generateToken(subject, claims);

        // Assert
        assertNotNull(token);

        // Decode and verify the token
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

        var parsedClaims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        assertEquals(subject, parsedClaims.getSubject());
        assertEquals("ADMIN", parsedClaims.get("role"));
        assertNotNull(parsedClaims.getIssuedAt());
        assertNotNull(parsedClaims.getExpiration());
    }

    @Test
    void generateToken_EmptySubject_StillGeneratesToken() {
        // Arrange
        String subject = "";
        Map<String, Object> claims = new HashMap<>();

        // Act
        String token = jwtTokenService.generateToken(subject, claims);

        // Assert
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void signInKey_CreatesValidKey() {
        // This is a private method test using reflection could be added if needed
        // Alternatively, we're testing it indirectly through token generation and decoding
        String subject = "testUser";
        String token = jwtTokenService.generateToken(subject, null);
        assertNotNull(token);
    }


}
