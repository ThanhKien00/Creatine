package io.creatine.account.domain;

import io.creatine.account.domain.command.CreateAccount;
import io.creatine.account.domain.command.TrackAccountLogin;
import io.creatine.account.domain.command.UpdateAccountProfile;
import io.creatine.account.domain.event.AccountCreated;
import io.creatine.account.domain.valueobject.Authority;
import io.creatine.account.domain.valueobject.Measurement;
import io.creatine.account.domain.valueobject.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Entity
@Table(name = "accounts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account extends AbstractAggregateRoot<Account> implements UserDetails {

    @Id
    private UUID id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String username;
    private String password;

    private String ipAddress;
    private LocalDateTime lastLogin;
    private Integer loginAttempts;
    private Boolean enabled;

    @Embedded
    private Profile profile;
    @OneToOne(mappedBy = "account", cascade = CascadeType.PERSIST)
    private Goal goal;
    @ElementCollection(fetch = FetchType.LAZY)
    private List<Measurement> measurements;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(Role::toGrantedAuthority).collect(Collectors.toList());
    }

    public static Account newAccount() {
        return new Account();
    }

    public Account register(CreateAccount command) {
        this.id = UUID.randomUUID();
        this.email = command.email();
        this.username = command.username();
        this.password = command.password();
        this.roles = List.of(Role.of(Authority.USER));
        this.profile = new Profile();
        this.enabled = true;
        this.loginAttempts = 0;


        registerEvent(AccountCreated.builder()
                .accountId(id)
                .email(email)
                .username(username)
                .password(password)
                .build());
        return this;
    }

    public Account trackLogin(TrackAccountLogin command) {

        this.ipAddress = command.ipAddress();
        this.lastLogin = LocalDateTime.now();
        this.loginAttempts += 1;

        return this;
    }

    public Account updateProfile(UpdateAccountProfile command) {
        this.profile.update(command);
        this.lastLogin = LocalDateTime.now();
        this.loginAttempts = 0; // Reset login attempts on profile update
        return this;
    }

    public void disable() {
        this.enabled = false; // Softly delete it by disabling the account
    }



}
