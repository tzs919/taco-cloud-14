package com.example.demo;

import com.example.demo.data.IngredientRepository;
import com.example.demo.domain.Ingredient;
import com.example.demo.domain.Ingredient.Type;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(IngredientRepository repo) {

        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Ingredient flourTortilla = saveAnIngredient("FLTO", "Flour Tortilla", Type.WRAP);
                Ingredient cornTortilla = saveAnIngredient("COTO", "Corn Tortilla", Type.WRAP);
                Ingredient groundBeef = saveAnIngredient("GRBF", "Ground Beef", Type.PROTEIN);
                Ingredient carnitas = saveAnIngredient("CARN", "Carnitas", Type.PROTEIN);
                Ingredient tomatoes = saveAnIngredient("TMTO", "Diced Tomatoes", Type.VEGGIES);
                Ingredient lettuce = saveAnIngredient("LETC", "Lettuce", Type.VEGGIES);
                Ingredient cheddar = saveAnIngredient("CHED", "Cheddar", Type.CHEESE);
                Ingredient jack = saveAnIngredient("JACK", "Monterrey Jack", Type.CHEESE);
                Ingredient salsa = saveAnIngredient("SLSA", "Salsa", Type.SAUCE);
                Ingredient sourCream = saveAnIngredient("SRCR", "Sour Cream", Type.SAUCE);
            }

            private Ingredient saveAnIngredient(String id, String name, Type type) {
                Ingredient ingredient = new Ingredient(id, name, type);
                repo.save(ingredient).subscribe();
                return ingredient;
            }
        };
    }
}
