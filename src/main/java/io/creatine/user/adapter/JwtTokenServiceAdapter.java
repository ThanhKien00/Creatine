package io.creatine.user.adapter;

import io.creatine.user.adapter.properties.JwtTokenProperties;
import io.creatine.user.api.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class JwtTokenServiceAdapter implements TokenService {

    private final JwtTokenProperties tokenProperties;
    private final Supplier<SecretKey> key = () -> Keys.hmacShaKeyFor(Decoders.BASE64.decode(tokenProperties.getSecretKey()));

    public String generateToken(String subject) {
        return generateToken(subject, Collections.emptyMap());
    }

    public String generateToken(String subject, Map<String, Object> claims) {
        return Jwts
                .builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + tokenProperties.getExpiresIn()))
                .signWith(key.get())
                .compact();
    }

    public String extractSubject(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final var claims = Jwts.parser().verifyWith(key.get()).build().parseSignedClaims(token).getPayload();
        return claimsResolver.apply(claims);
    }

}
