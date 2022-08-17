package com.abdulquadir.recipeapp.controllers;


import com.abdulquadir.recipeapp.commands.IngredientCommand;
import com.abdulquadir.recipeapp.commands.RecipeCommand;
import com.abdulquadir.recipeapp.commands.UnitOfMeasureCommand;
import com.abdulquadir.recipeapp.services.IngredientService;
import com.abdulquadir.recipeapp.services.RecipeService;
import com.abdulquadir.recipeapp.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequestMapping("/recipe")
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    private final UnitOfMeasureService uomService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService uomService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.uomService = uomService;
    }


    @GetMapping("/{recipeId}/ingredients")
    public String ingredientList(@PathVariable String recipeId, Model model){

        log.debug("Getting ingredient list for recipe id:" + recipeId);

        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));

        return "recipe/ingredient/ingredientList";
    }

    @GetMapping("/{recipeId}/ingredient/{ingredientId}/show")
    public String showRecipeIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model){

        log.debug("Getting ingredients for ingredient id: " + ingredientId);

        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));

        return "recipe/ingredient/showIngredient";
    }

    @GetMapping("/{recipeId}/ingredient/new")
    public String addRecipeIngredient(@PathVariable String recipeId, Model model){

        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));
        model.addAttribute("ingredient", ingredientCommand);

        // init uom
        ingredientCommand.setUom(new UnitOfMeasureCommand());

        model.addAttribute("uomList", uomService.listAllUoms());

        return "recipe/ingredient/ingredientForm";
    }


    @GetMapping("/{recipeId}/ingredient/{ingredientId}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model){

        log.debug("Getting ingredients for ingredient id: " + ingredientId);

        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));
        model.addAttribute("uomList", uomService.listAllUoms());

        return "recipe/ingredient/ingredientForm";
    }

    @GetMapping("/{recipeId}/ingredient/{ingredientId}/delete")
    public String deleteRecipeIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model){

        log.debug("Getting ingredients for ingredient id: " + ingredientId);
        ingredientService.deleteById(Long.valueOf(recipeId), Long.valueOf(ingredientId));

        return "redirect:/recipe/" + recipeId + "/ingredients";
    }


    @PostMapping("/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command){
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        log.debug("saved recipe id: " + savedCommand.getRecipeId() );
        log.debug("saved ingredient id: " + savedCommand.getId() );

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }

}
