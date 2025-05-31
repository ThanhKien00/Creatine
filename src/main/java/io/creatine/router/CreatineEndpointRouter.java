package io.creatine.router;

import io.creatine.account.api.AccountService;
import io.creatine.account.api.AuthenticationService;
import io.creatine.account.domain.command.AuthenticateAccount;
import io.creatine.account.domain.command.CreateAccount;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreatineEndpointRouter {

    private final AccountService accountService;
    private final AuthenticationService authService;

    @PostMapping("/api/auth/register")
    ResponseEntity<?> login(@RequestBody @Valid CreateAccount command) {
        var tokenResponse = authService.register(command);
        return ResponseEntity.ok()
                .body(tokenResponse);
    }

    @PostMapping("/api/auth/login")
    ResponseEntity<?> register(@RequestBody @Valid AuthenticateAccount command) {
        var tokenResponse = authService.authenticate(command);
        return ResponseEntity.ok(tokenResponse);
    }

    @PostMapping("/api/account/current")
    ResponseEntity<?> currentAccount() {
        var accountResponse = accountService.current();
        return ResponseEntity.ok(accountResponse);
    }

}
