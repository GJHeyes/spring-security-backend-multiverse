package com.multiverse.api.springsecuritybackendmultiverseapi.recipes;


import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface RecipeService {
    public List<Recipe> getAllRecipes();
    public Recipe getRecipeById(int recipeID);
    public Recipe addRecipe(HttpServletRequest token, RecipeRequest recipeRequest);
    public Recipe updateRecipe(RecipeRequest recipeRequest, int recipeID);
    public Recipe deleteRecipe(int recipeID) throws Exception;
}
