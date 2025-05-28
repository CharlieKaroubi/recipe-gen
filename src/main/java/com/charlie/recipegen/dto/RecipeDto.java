package com.charlie.recipegen.dto;

import com.charlie.recipegen.entity.NutritionalProfile;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class RecipeDto {
    private String title;
    private String authorName;
    private List<IngredientDto> ingredientDtos;
    private NutritionalProfile nutritionalProfile;
    private List<String> steps;
    private Integer servings;
}
