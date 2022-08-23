package com.abdulquadir.recipeapp.services;

import com.abdulquadir.recipeapp.commands.RecipeCommand;
import com.abdulquadir.recipeapp.converters.RecipeCommandToRecipe;
import com.abdulquadir.recipeapp.converters.RecipeToRecipeCommand;
import com.abdulquadir.recipeapp.exceptions.NotFoundException;
import com.abdulquadir.recipeapp.model.Recipe;
import com.abdulquadir.recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }


    @Override
    public Set<Recipe> getRecipes() {
        log.info("Inside getRecipes method of RecipeServiceImpl");
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }

    @Override
    public Recipe findById(Long id){
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if(!recipeOptional.isPresent()){
            throw new NotFoundException("Recipe Not Found, for ID value :" +id.toString() );
        }
        return  recipeOptional.get();
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long id) {
        return recipeToRecipeCommand.convert(findById(id));
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved RecipeId: "+ savedRecipe.getId());

        return recipeToRecipeCommand.convert(savedRecipe);
    }

}
