package io.creatine.account.domain.event;

import lombok.Builder;
import org.jmolecules.event.annotation.DomainEvent;

import java.util.UUID;

@Builder
@DomainEvent
public record AccountCreated(
        UUID accountId,
        String username,
        String email,
        String password) {

}