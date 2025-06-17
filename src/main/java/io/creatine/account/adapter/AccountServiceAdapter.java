package io.creatine.account.adapter;

import io.creatine.account.api.AccountService;
import io.creatine.account.api.mapper.AccountMapper;
import io.creatine.account.api.response.AccountResponse;
import io.creatine.account.domain.Account;
import io.creatine.account.domain.command.UpdateAccountProfile;
import io.creatine.account.domain.query.QueryAccountProfile;
import io.creatine.account.infrastructure.jpa.AccountRepository;
import io.creatine.support.UnauthenticatedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceAdapter implements AccountService, UserDetailsService {

    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with username '%s' not found", username)));
    }

    @Override
    public AccountResponse current() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthenticatedException("User is not authenticated");
        }
        var account = (Account) authentication.getPrincipal();
        return accountMapper.toResponse(account);
    }

    @Override
    public AccountResponse getProfile(QueryAccountProfile query) {
        UUID accountId = UUID.fromString(query.accountId());
        var account = accountRepository.findById(accountId)
                .orElseThrow(RuntimeException::new);

        return accountMapper.toResponse(account);
    }

    @Override
    public List<AccountResponse> limit() {
        return List.of();
    }

    @Override
    public AccountResponse updateProfile(UpdateAccountProfile command) {
        var accountId = UUID.fromString(command.accountId());
        var account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        account.updateProfile(command);
        accountRepository.save(account);
        return accountMapper.toResponse(account);
    }

    @Override
    public boolean delete(String accountId) {
        return false;
    }


}
