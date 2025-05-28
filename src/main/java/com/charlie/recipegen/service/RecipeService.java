package com.charlie.recipegen.service;

import com.charlie.recipegen.dto.IngredientDto;
import com.charlie.recipegen.dto.RecipeDto;
import com.charlie.recipegen.entity.Ingredient;
import com.charlie.recipegen.entity.Recipe;
import com.charlie.recipegen.repository.IngredientRepository;
import com.charlie.recipegen.repository.RecipeRepository;
import com.charlie.recipegen.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final UserRepository userRepository;

    public RecipeService(RecipeRepository recipeRepository, IngredientRepository ingredientRepository, UserRepository userRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.userRepository = userRepository;
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
    public RecipeDto saveRecipe(RecipeDto recipeDto) {
        Recipe recipe = Recipe.builder()
                .title(recipeDto.getTitle())
                .author(userRepository.findByDisplayName(recipeDto.getAuthorName()))
                .ingredients(this.ingredientsMapper(recipeDto.getIngredientDtos()))
                .steps(recipeDto.getSteps())
                .nutritionalProfile(recipeDto.getNutritionalProfile())
                .servings(recipeDto.getServings())
                .build();

        Recipe saved = this.recipeRepository.save(recipe);
        return this.recipeToDto(saved);
    }

    private List<Ingredient> ingredientsMapper(List<IngredientDto> ingredientDto) {
        return ingredientDto.stream().map(this::dtoToIngredient).toList();
    }

    private Ingredient dtoToIngredient(IngredientDto ingredientDto) {
        return ingredientRepository.findByNameIgnoreCase(ingredientDto.getName())
                .orElseGet(() -> ingredientRepository.save(new Ingredient(ingredientDto.getName())));
    }

    private RecipeDto recipeToDto(Recipe recipe) {
        return RecipeDto.builder()
                .title(recipe.getTitle())
                .ingredientDtos(
                        recipe.getIngredients().stream()
                                .map(ingredient -> new IngredientDto(ingredient.getName()))
                                .toList()
                )
                .nutritionalProfile(recipe.getNutritionalProfile())
                .steps(recipe.getSteps())
                .servings(recipe.getServings())
                .build();
    }


}
