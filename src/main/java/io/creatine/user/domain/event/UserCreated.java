package io.creatine.user.domain.event;

import org.jmolecules.event.annotation.DomainEvent;

@DomainEvent
public record UserCreated(
        String userId,
        String username,
        String email,
        String password) {

}