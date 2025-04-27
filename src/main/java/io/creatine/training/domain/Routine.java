package io.creatine.training.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Routine {

    @Id
    private Long id;
    private String title;

    /// [RoutineIdentifier] serves as an identifier type for [Routine] objects. The main reason for its
    /// existence is type safety for identifier across the Salespoint Framework.
    /// [] instances serve as primary key attribute in [], but can also be used
    /// as a key for non-persistent.
    ///
    /// @author Hannes Weisbach
    /// @author Oliver Gierke
    @Embeddable
    static class RoutineIdentifier {

    }
}
