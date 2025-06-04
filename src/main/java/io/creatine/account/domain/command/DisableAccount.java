package io.creatine.account.domain.command;

import io.creatine.sharedkernel.Command;

public record DisableAccount(String accountId) implements Command {
}
