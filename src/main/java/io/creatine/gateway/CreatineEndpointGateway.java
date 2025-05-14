package io.creatine.gateway;

import io.creatine.user.api.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreatineEndpointGateway {

    private final AuthenticationService authService;

    @PostMapping
    ResponseEntity<?> login() {
        return null;
    }

}
