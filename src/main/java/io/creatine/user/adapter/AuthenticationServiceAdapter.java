package io.creatine.user.adapter;

import io.creatine.user.api.AuthenticationService;
import io.creatine.user.api.TokenService;
import io.creatine.user.api.response.TokenResponse;
import io.creatine.user.domain.User;
import io.creatine.user.domain.command.UserCommand;
import io.creatine.user.infrastructure.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceAdapter implements AuthenticationService {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Override
    public TokenResponse register(UserCommand.CreateAccount command) {
        if (userRepository.existsByUsernameOrEmail(command.username(), command.email())) {
            throw new DataIntegrityViolationException("Username or email already exists");
        }
        var user = new User().handle(command);
        userRepository.save(user);
        return tokenService.generateToken(command.username());
    }

    @Override
    public TokenResponse login() {
        return null;
    }
}
