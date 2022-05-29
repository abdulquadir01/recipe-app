package com.abdulquadir.recipeapp.services;

import com.abdulquadir.recipeapp.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipies();
}
