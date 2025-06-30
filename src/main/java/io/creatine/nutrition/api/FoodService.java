package io.creatine.nutrition.api;

import io.creatine.nutrition.api.response.FoodResponse;
import io.creatine.nutrition.domain.command.CustomizeFood;

public interface FoodService {

    FoodResponse customize(CustomizeFood command);

    void update();

    void delete();

    void getOne(String foodId);

    void getByUser();

    void certificate();

}
