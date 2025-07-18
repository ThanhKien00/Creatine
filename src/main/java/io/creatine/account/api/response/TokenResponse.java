package io.creatine.account.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenResponse(
        String type,
        @JsonProperty("access_token") String accessToken,
        @JsonProperty("refresh_token") String refreshToken,
        @JsonProperty("expires_in") Long expiresIn,
        @JsonProperty("refresh_expires_in") Long refreshExpiresIn) {}
