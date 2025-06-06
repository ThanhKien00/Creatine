package io.creatine.nutrition.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Table(name = "menus")
@NoArgsConstructor
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int totalCalories;
    private boolean certified;


    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<FoodMenu> foodMenus;

    private void calculateCalories() {
        this.totalCalories = foodMenus.stream()
                .map(FoodMenu::getFoodCalories)
                .reduce(0, Integer::sum);
    }

}
