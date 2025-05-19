package io.creatine.user.domain;

import io.creatine.user.domain.command.UserCommand;
import io.creatine.user.domain.valueobject.Measurement;
import io.creatine.user.domain.valueobject.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jmolecules.ddd.annotation.AggregateRoot;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/// # User Aggregate
/// To manage system accounts, Creatine has a notion of a {@code User} in the form of User Interfaces.
///
/// @author ThanhKien

@Getter
@Entity
@AggregateRoot
@Table(name = "users")
@NoArgsConstructor
public class User extends AbstractAggregateRoot<User> implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String username;
    private String password;

    // Profile properties
    private String bio;
    private Integer age;
    private String lastName;
    private String firstName;
    private LocalDate birthday;
    private LocalDateTime lastLogin;
    private Integer loginAttempts;
    private String imageUrl;
    private boolean enabled;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<Measurement> measurements;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;

    @OneToOne(mappedBy = "user")
    private Goal goal;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.toAuthority()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Command Handling Methods
    public User handle(UserCommand.CreateAccount command) {
        this.id = UUID.randomUUID();
        this.username = command.username();
        this.email = command.email();
        this.password = command.password();
        this.enabled = false;
        this.roles = List.of();
//        registerEvent();
        return this;
    }

}
