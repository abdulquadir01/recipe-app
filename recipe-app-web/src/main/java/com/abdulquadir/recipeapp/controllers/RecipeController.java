package com.abdulquadir.recipeapp.controllers;


import com.abdulquadir.recipeapp.commands.RecipeCommand;
import com.abdulquadir.recipeapp.exceptions.NotFoundException;
import com.abdulquadir.recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
@Controller
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @GetMapping("/{id}/show")
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
        return "recipe/showRecipe";
    }


    @GetMapping("/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeForm";
    }

    @GetMapping("/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));

        return "recipe/recipeForm";
    }

    @GetMapping("/{id}/delete")
    public String deleteRecipe(@PathVariable String id){
        log.debug("Deleted id: "+ id);

         recipeService.deleteById(Long.valueOf(id));

         return "redirect:/recipes";
    }

    @PostMapping(value = "")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command){
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception) {
        log.error("Handling not found exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("exception", exception);

        modelAndView.setViewName("404Error");

        return modelAndView;

    }

}
