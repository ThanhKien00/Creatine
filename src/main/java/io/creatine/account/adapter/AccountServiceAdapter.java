package io.creatine.account.adapter;

import io.creatine.account.api.AccountService;
import io.creatine.account.api.mapper.AccountMapper;
import io.creatine.account.api.response.AccountResponse;
import io.creatine.account.domain.Account;
import io.creatine.account.infrastructure.jpa.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceAdapter implements AccountService, UserDetailsService {

    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("some msg" + username));
    }


    @Override
    public AccountResponse current() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            var account = (Account) authentication.getPrincipal();
            return accountMapper.toResponse(account);
        }
        return null;
    }
}
