package io.creatine.user.api;


import java.util.Date;
import java.util.Map;

public interface TokenService {


    String generateToken(String subject);

    String generateToken(String subject, Map<String, Object> claims);

    String extractSubject(String token);

    Date extractExpiration(String token);

}
