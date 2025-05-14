package io.creatine.user.domain.valueobject;

public record Token(
        String type,
        String accessToken,
        String refreshToken,
        Long expiresIn,
        Long refreshExpiresIn
) {

}
