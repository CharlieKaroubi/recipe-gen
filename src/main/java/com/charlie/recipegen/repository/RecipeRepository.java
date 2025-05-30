package com.charlie.recipegen.repository;

import com.charlie.recipegen.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Optional<Recipe> findByTitle(String title);
}
