package com.abdulquadir.recipeapp.services;

import com.abdulquadir.recipeapp.commands.RecipeCommand;
import com.abdulquadir.recipeapp.model.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();

    Recipe findById(Long id);

    void deleteById(Long id);

    RecipeCommand findCommandById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);
}
