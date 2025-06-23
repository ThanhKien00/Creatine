package io.creatine.account.domain;

import io.creatine.account.domain.command.UpdateAccountProfile;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profile {

    private String bio;
    private Integer age;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String imageUrl;
    private String address;

    public static Profile newProfile() {
        return new Profile();
    }

    void update(UpdateAccountProfile command) {
        this.bio = command.bio();
        this.age = command.age();
        this.firstName = command.firstName();
        this.lastName = command.lastName();
        this.birthday = command.birthday();
        this.imageUrl = command.imageUrl();
        this.address = command.address();
    }

}
