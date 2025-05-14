package io.creatine.user.adapter.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "creatine.jwt")
public class JwtTokenProperties {

    private String secretKey;
    private long expiresIn;
    private long refreshExpiresIn;

}
