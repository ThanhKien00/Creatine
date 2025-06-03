package io.creatine.account.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Embeddable
public class Profile {

    private String bio;
    private Integer age;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String imageUrl;
    private String address;

}
