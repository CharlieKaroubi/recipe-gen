package com.charlie.recipegen.dto;

import com.charlie.recipegen.entity.NutritionalProfile;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SaveRecipeDto {
    private String title;
    private List<IngredientDto> ingredientDtos;
    private NutritionalProfile nutritionalProfile;
    private List<String> steps;
    private Integer servings;
}
