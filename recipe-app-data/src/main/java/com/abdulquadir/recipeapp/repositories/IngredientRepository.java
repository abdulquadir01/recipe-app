package com.abdulquadir.recipeapp.repositories;

import com.abdulquadir.recipeapp.model.Ingredient;
import org.springframework.data.repository.CrudRepository;



public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

}
