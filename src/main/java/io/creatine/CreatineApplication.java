package io.creatine;

import io.creatine.nutrition.domain.Ingredient;
import io.creatine.nutrition.infrastructure.jpa.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;
import org.springframework.modulith.Modulith;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@EnableAsync
@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@Modulith(systemName = "Creatine Platform",
        additionalPackages = "io.creatine",
        useFullyQualifiedModuleNames = true,
        sharedModules = {
                "io.creatine.support",
                "io.creatine.sharedkernel"
        })
@RequiredArgsConstructor
public class CreatineApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CreatineApplication.class, args);
    }

    final IngredientRepository ingredientRepository;


    @Override
    public void run(String... args) throws Exception {
        if (ingredientRepository.count() != 0) {
            ingredientRepository.deleteAll();
            ingredientRepository.saveAll(ingredients());
        }

    }

    public List<Ingredient> ingredients() {
        return List.of(
                new Ingredient(1L, "Tomato", "https://example.com/images/tomato.jpg", 18.0),
                new Ingredient(2L, "Lettuce", "https://example.com/images/lettuce.jpg", 15.0),
                new Ingredient(3L, "Cheese", "https://example.com/images/cheese.jpg", 402.0),
                new Ingredient(4L, "Chicken Breast", "https://example.com/images/chicken_breast.jpg", 165.0),
                new Ingredient(5L, "Olive Oil", "https://example.com/images/olive_oil.jpg", 884.0),
                new Ingredient(6L, "Cucumber", "https://example.com/images/cucumber.jpg", 16.0),
                new Ingredient(7L, "Carrot", "https://example.com/images/carrot.jpg", 41.0),
                new Ingredient(8L, "Beef Patty", "https://example.com/images/beef_patty.jpg", 250.0),
                new Ingredient(9L, "Onion", "https://example.com/images/onion.jpg", 40.0),
                new Ingredient(10L, "Avocado", "https://example.com/images/avocado.jpg", 160.0),
                new Ingredient(11L, "Pineapple", "https://example.com/images/pineapple.jpg", 50.0),
                new Ingredient(12L, "Apple", "https://example.com/images/apple.jpg", 52.0),
                new Ingredient(13L, "Potato", "https://example.com/images/potato.jpg", 77.0),
                new Ingredient(14L, "Corn", "https://example.com/images/corn.jpg", 96.0),
                new Ingredient(15L, "Mushroom", "https://example.com/images/mushroom.jpg", 22.0),
                new Ingredient(16L, "Bacon", "https://example.com/images/bacon.jpg", 541.0),
                new Ingredient(17L, "Egg", "https://example.com/images/egg.jpg", 155.0),
                new Ingredient(18L, "Bell Pepper", "https://example.com/images/bell_pepper.jpg", 20.0),
                new Ingredient(19L, "Spinach", "https://example.com/images/spinach.jpg", 23.0),
                new Ingredient(20L, "Rice", "https://example.com/images/rice.jpg", 130.0)
        );

    }

}
