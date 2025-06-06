package io.creatine.nutrition.domain;

import io.creatine.nutrition.domain.valueobject.Instruction;
import io.creatine.nutrition.domain.valueobject.MacroNutrient;
import io.creatine.support.ListStringConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private int calories;
    private boolean certified;
    @Convert(converter = ListStringConverter.class)
    private List<String> tags;
    @Embedded
    private MacroNutrient macro;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Instruction> instructions;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private List<FoodMenu> foodMenus;

}
