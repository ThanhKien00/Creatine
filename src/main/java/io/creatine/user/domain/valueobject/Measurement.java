package io.creatine.user.domain.valueobject;

import jakarta.persistence.*;
import org.jmolecules.ddd.types.ValueObject;

import java.time.LocalDate;

@Embeddable
@Table(name = "measurements")
public record Measurement(
        LocalDate recordedDate,
        Double weight,
        Double height,
        Double bodyFat,
        Integer neck,
        Integer shoulder,
        Integer chest,
        Integer bicep,
        Integer forearm,
        String result) implements ValueObject {

}
