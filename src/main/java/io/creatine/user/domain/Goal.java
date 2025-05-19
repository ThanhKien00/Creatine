package io.creatine.user.domain;

import io.creatine.user.domain.valueobject.GoalTarget;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "goals")
@NoArgsConstructor
@org.jmolecules.ddd.annotation.Entity
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private GoalTarget goalTarget;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
