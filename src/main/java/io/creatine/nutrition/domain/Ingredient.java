package io.creatine.nutrition.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Ingredient {

    @Id
    private Long id;
    private String title;
    private String imageUrl;

}
