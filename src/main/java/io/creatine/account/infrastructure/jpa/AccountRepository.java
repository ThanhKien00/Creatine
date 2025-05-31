package io.creatine.account.infrastructure.jpa;

import io.creatine.account.domain.Account;
import org.jmolecules.ddd.annotation.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    Optional<Account> findByUsername(String username);

    Boolean existsByUsernameOrEmail(String username, String email);

}
