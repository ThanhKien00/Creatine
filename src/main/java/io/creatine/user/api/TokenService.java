package io.creatine.user.api;


import io.creatine.user.api.response.TokenResponse;

import java.util.Date;

public interface TokenService {

    TokenResponse generateToken(String subject);

    String extractSubject(String token);

    Date extractExpiration(String token);

}
