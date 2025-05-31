package io.creatine.account.domain.command;

public record AuthenticateAccount(String ipAddress, String username, String password) {}
