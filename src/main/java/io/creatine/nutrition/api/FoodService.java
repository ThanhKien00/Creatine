package io.creatine.nutrition.api;

public interface FoodService {

    void customize(CustomizeFood command);

    void update();

    void delete();

    void getOne(String foodId);

    void getByUser();

    void certificate();

}
