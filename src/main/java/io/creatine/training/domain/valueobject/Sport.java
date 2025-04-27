package io.creatine.training.domain.valueobject;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Sport {
    RUN("Run", ""),
    TRAIL_RUN("Trail Run", ""),
    WALK("", ""),
    HIKE("", ""),
    ;
    private final String title;
    private final String description;
}
