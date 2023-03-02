package com.multiverse.api.springsecuritybackendmultiverseapi.recipes;


import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RecipeService {
    public ResponseEntity<List<Recipe>> getAllRecipes(HttpServletRequest token);
    public Recipe getRecipeById(int recipeID);
    public Recipe addRecipe(HttpServletRequest token, RecipeRequest recipeRequest);
    public ResponseEntity<Recipe> updateRecipe(HttpServletRequest token, RecipeRequest recipeRequest, int recipeID);
    public Recipe deleteRecipe(int recipeID) throws Exception;
}
