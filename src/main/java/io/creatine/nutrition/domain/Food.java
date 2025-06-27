package io.creatine.nutrition.domain;

import io.creatine.nutrition.domain.command.CustomizeFood;
import io.creatine.nutrition.domain.valueobject.Instruction;
import io.creatine.nutrition.domain.valueobject.Macronutrient;
import io.creatine.support.ListStringConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "foods")
@NoArgsConstructor
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String imageUrl;
    private double calories;
    private boolean certified;
    @Convert(converter = ListStringConverter.class)
    private List<String> tags = new ArrayList<>();
    @Embedded
    private Macronutrient macronutrient;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Instruction> instructions = new ArrayList<>();

    @OneToMany(mappedBy = "food", cascade = CascadeType.PERSIST)
    private List<FoodMenu> foodMenus = new ArrayList<>();
    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private List<FoodIngredient> foodIngredients = new ArrayList<>();

    public static Food newFood() {
        return new Food();
    }

    public static Food newFoodWithIngredients(List<Ingredient> ingredients) {
        var food = new Food();
        var foodIngredients = ingredients.stream()
                .map(ingredient -> new FoodIngredient(food, ingredient))
                .toList();
        return food;
    }

    public Food customize(CustomizeFood command) {
        this.title = command.title();
        this.description = command.description();
        this.imageUrl = command.imageUrl();
        this.certified = false;
        this.tags.addAll(command.tags());
        this.macronutrient = command.macronutrient() == null ? new Macronutrient() : command.macronutrient();

        this.calories = this.foodIngredients.stream()
                .map(FoodIngredient::getActualCalories)
                .reduce(0D, Double::sum);
        return this;
    }

}
