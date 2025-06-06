package io.creatine.nutrition.domain;

import io.creatine.nutrition.domain.valueobject.Meal;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "foods_menus")
public class FoodMenu {

    @EmbeddedId
    private FoodMenuId id;
    @Enumerated(EnumType.STRING)
    private Meal meal;

    @ManyToOne
    @MapsId("foodId")
    @JoinColumn(referencedColumnName = "id")
    private Food food;
    @ManyToOne
    @MapsId("menuId")
    @JoinColumn(referencedColumnName = "id")
    private Menu menu;

    public Integer getFoodCalories() {
        return this.food.getCalories();
    }

    @Embeddable
    record FoodMenuId(
            Long foodId,
            Long menuId,
            Long day
    ) {
    }

}
