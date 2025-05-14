package io.creatine.user.domain.command;

public class UserCommand {

    public record CreateAccount(
            String username,
            String email,
            String password) {}

    public record AuthenticateAccount(
            String username,
            String password) {}

    public record UpdateUserProfile(String userId) {

    }

    public record DeleteUser() {}


}
