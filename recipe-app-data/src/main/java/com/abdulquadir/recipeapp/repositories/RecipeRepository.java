package com.abdulquadir.recipeapp.repositories;

import com.abdulquadir.recipeapp.model.Recipe;
import org.springframework.data.repository.CrudRepository;



public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
