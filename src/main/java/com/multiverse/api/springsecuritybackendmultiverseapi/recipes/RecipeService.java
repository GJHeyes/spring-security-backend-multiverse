package com.multiverse.api.springsecuritybackendmultiverseapi.recipes;


import java.util.List;

public interface RecipeService {
    public List<Recipe> getAllRecipes();
    public Recipe getRecipeById(int recipeID);
    public Recipe addRecipe(Recipe recipe);
    public Recipe updateRecipe(Recipe recipe, int recipeID);
    public Recipe deleteRecipe(int recipeID) throws Exception;
}
