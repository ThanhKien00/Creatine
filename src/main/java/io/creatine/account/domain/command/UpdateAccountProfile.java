package io.creatine.account.domain.command;

import java.time.LocalDate;

public record UpdateAccountProfile(
        String accountId,
        String bio,
        Integer age,
        String firstName,
        String lastName,
        LocalDate birthday,
        String imageUrl,
        String address
) {}
