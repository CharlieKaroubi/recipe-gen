package com.charlie.recipegen.service;

import com.charlie.recipegen.dto.IngredientDto;
import com.charlie.recipegen.dto.SaveRecipeDto;
import com.charlie.recipegen.entity.Ingredient;
import com.charlie.recipegen.entity.Recipe;
import com.charlie.recipegen.repository.IngredientRepository;
import com.charlie.recipegen.repository.RecipeRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    public RecipeService(RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public List<Recipe> getAllRecipes() {
        return this.recipeRepository.findAll();
    }

    public Recipe getRecipeById(Long id) {
        return this.recipeRepository.findById(id).orElse(null);
    }

    public Recipe getRecipeByTitle(String title) {
        return this.recipeRepository.findByTitle(title).orElse(null);
    }

    @Transactional
    public Recipe saveRecipe(SaveRecipeDto saveRecipeDto) {
        Recipe savedRecipe = Recipe.builder()
                .title(saveRecipeDto.getTitle())
                .ingredients(this.ingredientsMapper(saveRecipeDto.getIngredientDtos()))
                .steps(saveRecipeDto.getSteps())
                .nutritionalProfile(saveRecipeDto.getNutritionalProfile())
                .servings(saveRecipeDto.getServings())
                .build();

        return this.recipeRepository.save(savedRecipe);
    }

    private List<Ingredient> ingredientsMapper(List<IngredientDto> ingredientDto) {
        return ingredientDto.stream().map(this::dtoToIngredient).toList();
    }

    private Ingredient dtoToIngredient(IngredientDto ingredientDto) {
        return ingredientRepository.findByNameIgnoreCase(ingredientDto.getName())
                .orElseGet(() -> ingredientRepository.save(new Ingredient(ingredientDto.getName())));
    }


}
