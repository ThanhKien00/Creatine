package io.creatine.account.domain.valueobject;

import jakarta.persistence.*;
import lombok.Data;
import org.jmolecules.ddd.types.ValueObject;

import java.time.LocalDate;

@Data
@Embeddable
public class Measurement implements ValueObject {
    private LocalDate recordedDate;
    private Double weight;
    private Double height;
    private Double bodyFat;
    private Integer neck;
    private Integer shoulder;
    private Integer chest;
    private Integer bicep;
    private Integer forearm;
    private String result;
}
