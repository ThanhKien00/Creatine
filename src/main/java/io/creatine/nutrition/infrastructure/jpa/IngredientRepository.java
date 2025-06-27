package io.creatine.nutrition.infrastructure.jpa;

import io.creatine.nutrition.domain.Ingredient;
import org.jmolecules.ddd.annotation.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Stream;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    Stream<Ingredient> streamById(Long id);

}
