package com.abdulquadir.recipeapp.commands;


import com.abdulquadir.recipeapp.constants.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class RecipeCommand {

    private Long id;

    @NotBlank
    @Size(min=25, max=511)
    private String description;

    @Min(1) @Max(999)
    private Integer prepTime;

    @Min(1) @Max(999)
    private Integer cookTime;

    @Min(1) @Max(99)
    private Integer servings;
    private String source;

    @URL
    @NotBlank
    private String url;

    @NotBlank
    private String directions;

    private Byte[] image;
    private Set<IngredientCommand> ingredients  = new HashSet<>();
    private Difficulty difficulty;
    private NotesCommand notes;
    private Set<CategoryCommand> categories = new HashSet<>();

}
