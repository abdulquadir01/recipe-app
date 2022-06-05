package com.abdulquadir.recipeapp.controllers;

import com.abdulquadir.recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/")
    public String getHomePage(){
        log.info("inside getHomePage method - slf4j");
        return "index";
    }

    @RequestMapping("/recipes")
    public String getRecipePage(Model model){

        log.info("inside getRecipePage method - slf4j");
        model.addAttribute("recipes", recipeService.getRecipies());

        return "recipePage";
    }

}
