package io.creatine.user.domain.valueobject;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public record Goal(
        @Enumerated(EnumType.STRING) GoalTarget goalTarget,
        Double targetWeight) {}
