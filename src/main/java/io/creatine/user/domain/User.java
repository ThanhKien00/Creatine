package io.creatine.user.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import org.jmolecules.ddd.types.Identifier;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @EmbeddedId
    private UserIdentifier identifier;

    @Embeddable
    @NoArgsConstructor(force = true)
    static class UserIdentifier implements Identifier, Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        private UUID id;

    }
}
