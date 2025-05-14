package io.creatine.user.domain;

import io.creatine.user.domain.command.UserCommand;
import io.creatine.user.domain.valueobject.Goal;
import io.creatine.user.domain.valueobject.Measurement;
import io.creatine.user.domain.valueobject.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@Table(name = "users")
@NoArgsConstructor
public class User extends AbstractAggregateRoot<User> implements UserDetails {

    @Id
    private UUID id;

    // Account properties
    private String email;
    private String username;
    private String password;

    // Profile properties
    private String bio;
    private Integer age;
    private String lastName;
    private String firstName;
    private LocalDate birthday;
    private LocalDateTime lastLogin;
    private String imageUrl;
    private boolean enabled;

    @ElementCollection(fetch = FetchType.LAZY, targetClass = Measurement.class)
    @CollectionTable(name = "measurements", joinColumns = @JoinColumn(name = "user_id"))
    private List<Measurement> measurements;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;

    @Embedded
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
    public void handle(UserCommand.CreateAccount command) {


//        registerEvent();
    }


}
