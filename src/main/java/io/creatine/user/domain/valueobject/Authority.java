package io.creatine.user.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Authority {
    USER("USER"),
    ADMIN("ADMIN"),
    EXPERT("EXPERT"),
    NUTRITIONIST ("NUTRITIONIST");

    private final String name;
}
