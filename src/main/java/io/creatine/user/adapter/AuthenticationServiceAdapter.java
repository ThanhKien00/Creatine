package io.creatine.user.adapter;

import io.creatine.user.api.AuthenticationService;
import io.creatine.user.api.response.TokenResponse;
import io.creatine.user.domain.command.UserCommand;
import io.creatine.user.infrastructure.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceAdapter implements AuthenticationService {

    private final UserRepository userRepository;

    @Override
    public TokenResponse register(UserCommand.CreateAccount command) {
        return null;

    }

    @Override
    public TokenResponse login() {
        return null;
    }
}
