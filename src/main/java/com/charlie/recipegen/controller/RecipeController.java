package com.charlie.recipegen.controller;

import com.charlie.recipegen.dto.RecipeDto;
import com.charlie.recipegen.entity.Recipe;
import com.charlie.recipegen.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;
    @Autowired
    public RecipeController(RecipeService recipeService) {this.recipeService = recipeService;}

    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        List<Recipe> recipes = recipeService.getAllRecipes();
        return ResponseEntity.ok(recipes);
    }

    @PostMapping("/save")
    public ResponseEntity<RecipeDto> saveRecipe(@RequestBody RecipeDto recipeDto) {
        RecipeDto savedRecipe = recipeService.saveRecipe(recipeDto);
        return ResponseEntity.ok(savedRecipe);
    }
}
