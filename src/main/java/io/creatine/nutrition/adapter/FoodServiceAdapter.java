package io.creatine.nutrition.adapter;

import io.creatine.nutrition.api.FoodService;
import io.creatine.nutrition.api.response.FoodResponse;
import io.creatine.nutrition.domain.Food;
import io.creatine.nutrition.domain.Ingredient;
import io.creatine.nutrition.domain.command.CustomizeFood;
import io.creatine.nutrition.infrastructure.jpa.FoodRepository;
import io.creatine.nutrition.infrastructure.jpa.IngredientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodServiceAdapter implements FoodService {

    private final FoodRepository foodRepository;
    private final IngredientRepository ingredientRepository;

    @Override
    @Transactional
    public FoodResponse customize(CustomizeFood command) {
        var ingredientIds = command.foodIngredients().stream()
                .map(CustomizeFood.AddFoodIngredient::ingredientId)
                .toList();
        var ingredients = ingredientRepository.findAllById(ingredientIds);
        var food = Food.newFoodWithIngredients(ingredients)
                .customize();

        return null;
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void getOne(String foodId) {

    }

    @Override
    public void getByUser() {

    }

    @Override
    public void certificate() {

    }
}
