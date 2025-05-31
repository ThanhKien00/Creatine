package io.creatine.account.api;

import io.creatine.account.api.response.TokenResponse;
import io.creatine.account.domain.command.AuthenticateAccount;
import io.creatine.account.domain.command.CreateAccount;

public interface AuthenticationService {

    TokenResponse register(CreateAccount command);

    TokenResponse authenticate(AuthenticateAccount command);

    void changePassword();

}
