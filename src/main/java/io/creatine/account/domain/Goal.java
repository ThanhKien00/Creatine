package io.creatine.account.domain;

import io.creatine.account.domain.valueobject.ActivityLevel;
import io.creatine.account.domain.valueobject.TargetGoal;
import io.creatine.account.domain.valueobject.WeeklyGoal;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "goals")
@NoArgsConstructor
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double goalWeight;
    @Enumerated(EnumType.STRING)
    private ActivityLevel activityLevel;
    @Enumerated(EnumType.STRING)
    private TargetGoal targetGoal;
    @Enumerated(EnumType.STRING)
    private WeeklyGoal weeklyGoal;

    @OneToOne
    @JoinColumn(
            name = "account_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_goal_account"))
    private Account account;

}
