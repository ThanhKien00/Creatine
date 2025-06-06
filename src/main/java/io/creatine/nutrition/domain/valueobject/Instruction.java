package io.creatine.nutrition.domain.valueobject;

import jakarta.persistence.Embeddable;

@Embeddable
public class Instruction {

    private int step;
    private String description;

}
