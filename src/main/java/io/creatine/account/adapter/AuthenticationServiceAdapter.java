package io.creatine.account.adapter;

import io.creatine.account.api.AuthenticationService;
import io.creatine.account.api.TokenService;
import io.creatine.account.api.response.TokenResponse;
import io.creatine.account.domain.Account;
import io.creatine.account.domain.command.AuthenticateAccount;
import io.creatine.account.domain.command.CreateAccount;
import io.creatine.account.domain.command.TrackAccountLogin;
import io.creatine.account.domain.valueobject.Role;
import io.creatine.account.infrastructure.jpa.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceAdapter implements AuthenticationService {

    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    public TokenResponse register(CreateAccount command) {
        if (accountRepository.existsByUsernameOrEmail(command.username(), command.email())) {
            throw new DataIntegrityViolationException("Username or email already exists");
        }

        var account = Account.newAccount().register(command);
        accountRepository.save(account);

        Map<String, Object> claims = account.getRoles()
                .stream()
                .collect(Collectors.toMap(_ -> "roles", Role::authority));
        return tokenService.generateToken(account.getUsername(), claims);
    }

    @Override
    public TokenResponse authenticate(AuthenticateAccount command) {
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(command.username(), command.password()));
        var account = (Account) authentication.getPrincipal();
        var trackLoginCommand = new TrackAccountLogin(command.ipAddress());

        accountRepository.save(account.trackLogin(trackLoginCommand));
        Map<String, Object> claims = account.getRoles()
                .stream()
                .collect(Collectors.toMap(_ -> "roles", Role::authority));

        return tokenService.generateToken(account.getUsername(), claims);
    }

    @Override
    public void changePassword() {

    }

    @Override
    public void forgotPassword() {

    }
}
