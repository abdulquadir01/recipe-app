package com.abdulquadir.recipeapp.controllers;

import com.abdulquadir.recipeapp.commands.IngredientCommand;
import com.abdulquadir.recipeapp.commands.RecipeCommand;
import com.abdulquadir.recipeapp.services.IngredientService;
import com.abdulquadir.recipeapp.services.RecipeService;
import com.abdulquadir.recipeapp.services.UnitOfMeasureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@ExtendWith(MockitoExtension.class)
class IngredientControllerTest {
    @Mock
    RecipeService recipeService;
    @Mock
    UnitOfMeasureService uomService;

    @Mock
    IngredientService ingredientService;

    IngredientController ingredientController;
    MockMvc mockMvc;



    @BeforeEach
    void setUp() {
        ingredientController = new IngredientController(recipeService, ingredientService, uomService);
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
    }

    @Test
    public void ingredientListTest() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingredientList"))
                .andExpect(model().attributeExists("recipe"));

        verify(recipeService, times(1)).findCommandById(anyLong());
    }
    @Test
    public void showListTest() throws Exception {
        IngredientCommand ingredientCommand = new IngredientCommand();
//        when(ingredientService.findByRecipeIdAndId(anyLong(), anyLong())).thenReturn(ingredientCommand);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredient/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/showIngredient"))
                .andExpect(model().attributeExists("ingredient"));

//        verify(recipeService, times(1)).findCommandById(anyLong());
    }
}