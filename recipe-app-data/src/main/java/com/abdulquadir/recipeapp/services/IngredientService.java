package com.abdulquadir.recipeapp.services;

import com.abdulquadir.recipeapp.commands.IngredientCommand;

public interface IngredientService {

//    Set<Ingredient> getIngredients();
//
//    Ingredient findById(Long id);
//
    void deleteById(Long recipeId, Long idToDelete);
    IngredientCommand saveIngredientCommand(IngredientCommand command);

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
