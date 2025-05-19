package io.creatine.user.domain.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserCommand {

    public record CreateAccount(

            @Size(min = 6, max = 30, message = "Username must be between 3 and 30 characters.")
            @Pattern(regexp = "^[a-zA-Z0-9._-]+$", message = "Username can only contain letters, numbers, dots, underscores, and hyphens.")
            String username,

            @NotBlank(message = "Email is required.")
            @Email(message = "Invalid email format.")
            String email,

            @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters.")
            @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
                    message = "Password must contain at least one digit, one lowercase letter, one uppercase letter, and one special character.")
            String password
    ) {}


    public record AuthenticateAccount(
            @NotBlank(message = "Username is required.")
            String username,

            @NotBlank(message = "Password is required.")
            String password) {}

    public record UpdateUserProfile(String userId) {

    }

    public record DeleteUser() {}


}
