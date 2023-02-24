package com.multiverse.api.springsecuritybackendmultiverseapi.recipes;


import org.springframework.stereotype.Service;

import java.util.List;

public interface RecipeService {
    public List<Recipes> getAllRecipes();
    public Recipes getRecipeById(int recipeID);
    public Recipes addRecipe(Recipes recipe);
    public Recipes updateRecipe(Recipes recipe, int recipeID);
    public Recipes deleteRecipe(int recipeID) throws Exception;
}
