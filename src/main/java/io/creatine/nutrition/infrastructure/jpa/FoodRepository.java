package io.creatine.nutrition.infrastructure.jpa;

import io.creatine.nutrition.domain.Food;
import org.jmolecules.ddd.annotation.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
}
