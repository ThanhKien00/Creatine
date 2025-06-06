package io.creatine.nutrition.api;

public interface FoodService {

    void create();

    void update();

    void delete();

    void getOne(String foodId);

    void getByUser();

    void certificate();

}
