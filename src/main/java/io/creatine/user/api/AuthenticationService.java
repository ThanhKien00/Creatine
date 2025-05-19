package io.creatine.user.api;

import io.creatine.user.api.response.TokenResponse;
import io.creatine.user.domain.command.UserCommand;
import jakarta.validation.Valid;

public interface AuthenticationService {

    TokenResponse register(@Valid UserCommand.CreateAccount command);

    TokenResponse login();

}
