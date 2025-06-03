package io.creatine.router;

import io.creatine.account.api.AccountService;
import io.creatine.account.api.AuthenticationService;
import io.creatine.account.domain.command.AuthenticateAccount;
import io.creatine.account.domain.command.CreateAccount;
import io.creatine.support.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreatineEndpointRouter {

    private final AccountService accountService;
    private final AuthenticationService authService;

    @PostMapping(path = "/api/auth/register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<?> login(@Valid CreateAccount command) {
        var tokenResponse = authService.register(command);
        var apiResponse = ApiResponse.builder().data(tokenResponse).isSuccess(true).build();
        return ResponseEntity.ok().body(apiResponse);
    }

    @PostMapping(value = "/api/auth/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<?> register(@Valid AuthenticateAccount command) {
        var tokenResponse = authService.authenticate(command);
        var apiResponse = ApiResponse.builder().data(tokenResponse).isSuccess(true).build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/api/account/current")
    ResponseEntity<?> currentAccount() {
        var accountResponse = accountService.current();
        return ResponseEntity.ok(accountResponse);
    }

}
