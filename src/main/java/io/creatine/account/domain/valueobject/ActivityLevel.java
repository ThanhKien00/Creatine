package io.creatine.account.domain.valueobject;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ActivityLevel {
    SEDENTARY("Sedentary", "Little to no exercise, desk job", 1.2),
    LIGHTLY_ACTIVE("Lightly Active", "Light exercise 1-3 days per week", 1.375),
    MODERATELY_ACTIVE("Moderately Active", "Moderate exercise 3-5 days per week", 1.55),
    VERY_ACTIVE("Very Active", "Heavy exercise 6-7 days per week", 1.725),
    EXTREMELY_ACTIVE("Extremely Active", "Very heavy exercise, physical job, or training twice a day", 1.9);

    private final String displayName;
    private final String description;
    private final double multiplier; // For BMR calculations

    @Override
    public String toString() {
        return displayName + ": " + description;
    }
}