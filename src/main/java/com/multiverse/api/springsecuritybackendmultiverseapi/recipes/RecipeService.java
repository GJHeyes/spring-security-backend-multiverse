package com.multiverse.api.springsecuritybackendmultiverseapi.recipes;


import java.util.List;

public interface RecipeService {
    public List<Recipes> getAllRecipes();
    public Recipes getRecipeById(int recipeID);
    public Recipes addRecipe(Recipes recipe);
    public Recipes updateRecipe(Recipes recipe);
    public Recipes deleteRecipe(int recipeID) throws Exception;
}
