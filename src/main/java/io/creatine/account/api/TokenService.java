package io.creatine.account.api;


import io.creatine.account.api.response.TokenResponse;

import java.util.Date;
import java.util.Map;

public interface TokenService {

    TokenResponse generateToken(String subject);

    TokenResponse generateToken(String subject, Map<String, Object> claims);

    String extractSubject(String token);

    Date extractExpiration(String token);

}
