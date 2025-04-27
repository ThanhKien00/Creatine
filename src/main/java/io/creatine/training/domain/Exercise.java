package io.creatine.training.domain;

import io.creatine.training.domain.valueobject.ActivityLevel;
import io.creatine.training.domain.valueobject.Sport;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.jmolecules.ddd.types.Identifier;

import java.io.Serializable;
import java.time.Duration;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Exercise {

    @Id
    private String id;
    private String title;
    private String tags;
    private String author;
    private String description;
    private Integer burnedCalories;
    private Sport sport;
    private Duration duration;
    private ActivityLevel level;


    static class ExerciseIdentifier implements Identifier {

    }
}
