package io.creatine.nutrition.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "foods_ingredients")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FoodIngredient {

    @EmbeddedId
    private FoodIngredientId id;
    private double actualWeight;
    private double actualCalories;
    private String preparation;

    @ManyToOne
    @MapsId("foodId")
    @JoinColumn(referencedColumnName = "id")
    private Food food;
    @ManyToOne
    @MapsId("ingredientId")
    @JoinColumn(referencedColumnName = "id")
    private Ingredient ingredient;

    @Embeddable
    record FoodIngredientId(
            Long foodId,
            Long ingredientId
    ) {}

    public FoodIngredient(Food food, Ingredient ingredient) {
        this.food = food;
        this.ingredient = ingredient;
        this.id = new FoodIngredientId(food.getId(), ingredient.getId());
    }
    
}
