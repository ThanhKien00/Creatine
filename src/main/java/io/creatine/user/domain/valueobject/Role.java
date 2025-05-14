package io.creatine.user.domain.valueobject;

import jakarta.persistence.Embeddable;


@Embeddable
public record Role(String type) {

    public static final String ROLE_PREFIX = "ROLE_";

    public static Role of(String name) {
        return new Role(name);
    }

    public String toAuthority() {
        return type.startsWith(ROLE_PREFIX) ? type : ROLE_PREFIX.concat(type);
    }



}
