package com.abdulquadir.recipeapp.controllers;

import com.abdulquadir.recipeapp.model.Category;
import com.abdulquadir.recipeapp.model.UnitOfMeasure;
import com.abdulquadir.recipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/")
    public String getHomePage(){
        return "index";
    }

    @RequestMapping("/recipes")
    public String getRecipePage(Model model){
        model.addAttribute("recipes", recipeService.getRecipies());

        return "recipePage";
    }

}
