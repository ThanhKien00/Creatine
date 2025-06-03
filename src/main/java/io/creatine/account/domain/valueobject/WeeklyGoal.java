package io.creatine.account.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WeeklyGoal {
    LOSE_1_KG("Lose 1.0 kg per week", -1.0, -1100),
    LOSE_0_75_KG("Lose 0.75 kg per week", -0.75, -825),
    LOSE_0_5_KG("Lose 0.5 kg per week", -0.5, -550),
    LOSE_0_25_KG("Lose 0.25 kg per week", -0.25, -275),
    MAINTAIN("Maintain current weight", 0.0, 0),
    GAIN_0_25_KG("Gain 0.25 kg per week", 0.25, 275),
    GAIN_0_5_KG("Gain 0.5 kg per week", 0.5, 550),
    GAIN_0_75_KG("Gain 0.75 kg per week", 0.75, 825),
    GAIN_1_KG("Gain 1.0 kg per week", 1.0, 1100);

    private final String displayName;
    private final double weeklyChangeKg;
    private final int dailyCalorieAdjustment;

    public boolean isWeightLoss() {
        return weeklyChangeKg < 0;
    }

    public boolean isWeightGain() {
        return weeklyChangeKg > 0;
    }

    public boolean isMaintenance() {
        return weeklyChangeKg == 0;
    }

    public GoalIntensity getIntensity() {
        double absChange = Math.abs(weeklyChangeKg);
        if (absChange == 0) return GoalIntensity.MAINTENANCE;
        if (absChange <= 0.25) return GoalIntensity.MILD;
        if (absChange <= 0.5) return GoalIntensity.MODERATE;
        if (absChange <= 0.75) return GoalIntensity.AGGRESSIVE;
        return GoalIntensity.EXTREME;
    }

    public boolean isSafe() {
        // Generally safe weekly weight change is ±1kg or less
        return Math.abs(weeklyChangeKg) <= 1.0;
    }

    public String getRecommendation() {
        if (isMaintenance()) {
            return "Balanced approach for weight maintenance";
        }

        GoalIntensity intensity = getIntensity();
        return switch (intensity) {
            case MILD -> "Sustainable and easy to maintain long-term";
            case MODERATE -> "Good balance between results and sustainability";
            case AGGRESSIVE -> "Faster results but requires strict adherence";
            case EXTREME -> "Maximum rate - requires careful monitoring";
            default -> "";
        };
    }

    @Override
    public String toString() {
        return displayName;
    }

    public enum GoalIntensity {
        MAINTENANCE, MILD, MODERATE, AGGRESSIVE, EXTREME
    }
}