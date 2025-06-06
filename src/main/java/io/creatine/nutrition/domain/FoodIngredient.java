package io.creatine.nutrition.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class FoodIngredient {

    @EmbeddedId
    private FoodIngredientId id;
    private double actualWeight;


    @ManyToOne
    @MapsId("foodId")
    @JoinColumn(referencedColumnName = "id")
    private Food food;
    @ManyToOne
    @MapsId("ingredient")
    @JoinColumn(referencedColumnName = "id")
    private Ingredient ingredient;

    record FoodIngredientId(
            Long foodId,
            Long ingredientId
    ) {

    }
}
