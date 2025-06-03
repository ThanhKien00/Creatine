package io.creatine.account.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TargetGoal {
    LOSE_WEIGHT("Lose Weight", "Weight Loss", -0.5, -1.0),
    MAINTAIN_WEIGHT("Maintain Weight", "Weight Maintenance", 0.0, 0.0),
    GAIN_WEIGHT("Gain Weight", "Weight Gain", 0.25, 0.5);

    private final String displayName;
    private final String description;
    private final double minWeeklyChangeKg; // kg per week
    private final double maxWeeklyChangeKg; // kg per week

    /**
     * Get daily calorie adjustment needed for this goal
     * @return calories per day adjustment (negative for deficit, positive for surplus)
     */
    public int getDailyCalorieAdjustment() {
        double avgWeeklyChange = (minWeeklyChangeKg + maxWeeklyChangeKg) / 2.0;
        // 1 kg of body weight ≈ 7700 calories
        return (int) Math.round((avgWeeklyChange * 7700) / 7.0);
    }

    public boolean isWeightLoss() {
        return this == LOSE_WEIGHT;
    }

    public boolean isWeightGain() {
        return this == GAIN_WEIGHT;
    }

    public boolean isMaintenance() {
        return this == MAINTAIN_WEIGHT;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
