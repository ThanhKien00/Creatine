package io.creatine.account.api.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AccountResponse(
        String email,
        String username,
        String password,
        String bio,
        Integer age,
        String firstName,
        String lastName,
        LocalDate birthday,
        String imageUrl,
        String ipAddress,
        LocalDateTime lastLogin,
        Integer loginAttempts,
        Boolean enabled) {

}
