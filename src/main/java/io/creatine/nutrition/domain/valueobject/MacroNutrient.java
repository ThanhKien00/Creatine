package io.creatine.nutrition.domain.valueobject;

import jakarta.persistence.Embeddable;

@Embeddable
public class MacroNutrient {

    private double protein;
    private double carbohydrate;
    private double fat;

}
