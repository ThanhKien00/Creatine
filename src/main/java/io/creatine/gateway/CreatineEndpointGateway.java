package io.creatine.gateway;

import io.creatine.user.api.AuthenticationService;
import io.creatine.user.domain.command.UserCommand;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreatineEndpointGateway {

    private final AuthenticationService authService;

    @PostMapping("/api/auth/login")
    ResponseEntity<?> login(@RequestBody @Valid UserCommand.CreateAccount command) {
        var tokenResponse = authService.register(command);
        return ResponseEntity.ok()
                .body(tokenResponse);
    }

}
