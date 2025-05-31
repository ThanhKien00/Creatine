package io.creatine.account.domain.valueobject;

import jakarta.persistence.Embeddable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


@Embeddable
public record Role(Authority authority) {

    public static Role of(Authority authority) {
        return new Role(authority);
    }

    public SimpleGrantedAuthority toGrantedAuthority() {
        return new SimpleGrantedAuthority(authority.name());
    }

}

