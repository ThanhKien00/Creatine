package io.creatine.account.adapter;

import io.creatine.account.api.TokenService;
import io.creatine.account.api.response.TokenResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenServiceAdapter implements TokenService {

    private final JwtTokenProperties tokenProperties;

    public String generateAccessToken(String subject, Map<String, Object> claims) {
        return generateToken(subject, claims, tokenProperties.getExpiresIn());
    }

    public String generateRefreshToken(String subject, Map<String, Object> claims) {
        return generateToken(subject, claims, tokenProperties.getRefreshExpiresIn());
    }

    public TokenResponse generateToken(String subject) {
        return generateToken(subject, Collections.emptyMap());
    }

    public TokenResponse generateToken(String subject, Map<String, Object> claims) {
        return new TokenResponse("Bearer",
                generateAccessToken(subject, claims),
                generateRefreshToken(subject, claims),
                tokenProperties.getExpiresIn(),
                tokenProperties.getRefreshExpiresIn());
    }

    public String generateToken(String subject, Map<String, Object> claims, long expiration) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date())
                .issuer(tokenProperties.getIssuer())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(secretKey().get())
                .compact();
    }

    public String extractSubject(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final var claims = Jwts.parser().verifyWith(secretKey().get()).build().parseSignedClaims(token).getPayload();
        return claimsResolver.apply(claims);
    }

    private Supplier<SecretKey> secretKey() {
        return () -> Keys.hmacShaKeyFor(Decoders.BASE64.decode(tokenProperties.getSecretKey()));
    }

    @Getter
    @Setter
    @Component
    @ConfigurationProperties(prefix = "creatine.jwt")
    public static class JwtTokenProperties {
        private String issuer;
        private String secretKey;
        private long expiresIn;
        private long refreshExpiresIn;
    }

}
