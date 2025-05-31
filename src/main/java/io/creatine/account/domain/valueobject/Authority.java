package io.creatine.account.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Authority {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    COACH("ROLE_COACH"),
    EXPERT("ROLE_EXPERT"),
    NUTRITIONIST ("ROLE_NUTRITIONIST");

    private final String name;
}
