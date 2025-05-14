package io.creatine.user.api;

import io.creatine.user.api.response.TokenResponse;
import io.creatine.user.domain.command.UserCommand;

public interface AuthenticationService {

    TokenResponse register(UserCommand.CreateAccount command);

    TokenResponse login();

}
