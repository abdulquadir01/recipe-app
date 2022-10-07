package com.abdulquadir.recipeapp.bootstrap;

import com.abdulquadir.recipeapp.constants.Difficulty;
import com.abdulquadir.recipeapp.model.*;
import com.abdulquadir.recipeapp.repositories.CategoryRepository;
import com.abdulquadir.recipeapp.repositories.RecipeRepository;
import com.abdulquadir.recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@Profile("default")
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public DataLoader(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipes());
        log.debug("Loading Bootstrap Data.");
    }

    private List<Recipe> getRecipes(){

        log.info("inside getRecipes method - slf4j");

        List<Recipe> recipes = new ArrayList<>(2);

        log.info("getting the UnitOfMeasure(UOM) for guacamole from h2-db");
        //get UOM
        UnitOfMeasure eachUom = getUnitOfMeasure("Each");
        UnitOfMeasure tableSpoonUom = getUnitOfMeasure("Tablespoon");
        UnitOfMeasure teaSpoonUom = getUnitOfMeasure("Teaspoon");
        UnitOfMeasure dashUom = getUnitOfMeasure("Dash");
        UnitOfMeasure pinchUom = getUnitOfMeasure("Pinch");;
        UnitOfMeasure pintUom = getUnitOfMeasure("Pint");
        UnitOfMeasure cupUom = getUnitOfMeasure("Cup");;

        log.info("getting the category for guacamole from h2-db");
        //get Categories
        Category americanCategory = getCategory("American");
        Category mexicanCategory = getCategory("Mexican");

        log.info("Making the recipe for Guacamole");
        //Guacamole
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections(
                "1. Cut the avocado: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. Place in a bowl." + "\n" +
                "2. Mash the avocado flesh: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" + "\n" +
                "3. Add the remaining ingredients to taste: " + "\n" +
                    "Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                    "\n" +
                    "Add the chopped onion, cilantro, black pepper, and chilies. Chili peppers vary individually in their spiciness. So, start with a half of one chili pepper and add more to the guacamole to your desired degree of heat."
                    + "\n" +
                "4. Serve immediately:\n" +
                        "If making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.)\n" +
                        "\n" +
                        "Garnish with slices of red radish or jigama strips. Serve with your choice of store-bought tortilla chips or make your own homemade tortilla chips."

        );

        log.info("creating notes for guacamole");
        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("Chilling tomatoes hurts their flavor. So, if you want to add chopped tomato to your guacamole, add it just before serving.");

        guacRecipe.setNotes(guacNotes);

        log.info("adding ingredient information for guacamole");
        guacRecipe.addIngredient(new Ingredient("ripe Avocados", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal(5 ), teaSpoonUom));
        guacRecipe.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("serrano chilies, steams and seeds removed, minced", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Cilantro", new BigDecimal(2), tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("freshly granted clack pepper", new BigDecimal(2), dashUom));
        guacRecipe.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(2), eachUom));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        guacRecipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacRecipe.setServings(2);
        guacRecipe.setSource("Simply Recipes");

        log.info("adding guacamole in a list name recipes ");
        //adding to recipe list
        recipes.add(guacRecipe);

        //Tacos
        Recipe tacoRecipe = new Recipe();
        tacoRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacoRecipe.setCookTime(9);
        tacoRecipe.setPrepTime(20);
        tacoRecipe.setDifficulty(Difficulty.MODERATE);

        tacoRecipe.setDirections(
                "1. Prepare a gas or charcoal grill for medium-high, direct heat. " + "\n" +
                "2. Make the marinade and coat the chicken:\n" +
                    "In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                    "\n" +
                    "Set aside to marinate while the grill heats and you prepare the rest of the toppings." + "\n" +
                "3. Grill the chicken:\n" +
                    "Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes." + "\n" +
                "4. Warm the tortillas:\n" +
                        "Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                        "\n" +
                        "Wrap warmed tortillas in a tea towel to keep them warm until serving. " + "\n" +
                "5. Assemble the tacos:\n" +
                        "Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges." + "\n"
        );

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online. \n (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)");

        tacoRecipe.setNotes(tacoNotes);

        tacoRecipe.addIngredient(new Ingredient("Ancho Chili Powder", new BigDecimal(2), tableSpoonUom));
        tacoRecipe.addIngredient(new Ingredient("Dried Oregano", new BigDecimal(1), tableSpoonUom));
        tacoRecipe.addIngredient(new Ingredient("Sugar", new BigDecimal(1), tableSpoonUom));
        tacoRecipe.addIngredient(new Ingredient("Salt", new BigDecimal(5), teaSpoonUom));
        tacoRecipe.addIngredient(new Ingredient("Clove of Garlic, Chopped", new BigDecimal(1), eachUom));
        tacoRecipe.addIngredient(new Ingredient("finely grated orange zestr", new BigDecimal(1), tableSpoonUom));
        tacoRecipe.addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tableSpoonUom));
        tacoRecipe.addIngredient(new Ingredient("Olive oil", new BigDecimal(2), tableSpoonUom));
        tacoRecipe.addIngredient(new Ingredient("boneless chicken thighs", new BigDecimal(4), eachUom));
        tacoRecipe.addIngredient(new Ingredient("small corn tortillas", new BigDecimal(8), eachUom));
        tacoRecipe.addIngredient(new Ingredient("medium ripe avocados, sliced", new BigDecimal(2), eachUom));
        tacoRecipe.addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal(4), eachUom));
        tacoRecipe.addIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal(".5"), pintUom));
        tacoRecipe.addIngredient(new Ingredient("red onion, thinly slicked", new BigDecimal(".25"), eachUom));
        tacoRecipe.addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), eachUom));
        tacoRecipe.addIngredient(new Ingredient("Cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), cupUom));
        tacoRecipe.addIngredient(new Ingredient("line, cut into wedges", new BigDecimal(4), eachUom));

        tacoRecipe.getCategories().add(mexicanCategory);
        tacoRecipe.getCategories().add(americanCategory);

        tacoRecipe.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        tacoRecipe.setServings(4);
        guacRecipe.setSource("Simply Recipes");


        //adding to recipe list
        recipes.add(tacoRecipe);

        System.out.println("Size of recipes: "+ recipes.size());

        return recipes;
    }

    private UnitOfMeasure getUnitOfMeasure(String uomDesc){
        return unitOfMeasureRepository
                .findByDescription(uomDesc)
                .orElseThrow(
                        ()-> new RuntimeException("Unit of measure " + uomDesc + " not found!!")
                );
    }

    private Category getCategory(String description){
        return categoryRepository
                .findByDescription(description)
                .orElseThrow(
                        ()-> new RuntimeException(" Category " + description + " not found!!")
                );
    }

}
