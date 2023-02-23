package com.multiverse.api.springsecuritybackendmultiverseapi.recipes.impl;

import com.multiverse.api.springsecuritybackendmultiverseapi.recipes.RecipeRepository;
import com.multiverse.api.springsecuritybackendmultiverseapi.recipes.RecipeService;
import com.multiverse.api.springsecuritybackendmultiverseapi.recipes.Recipes;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public List<Recipes> getAllRecipes() {
        return (List<Recipes>) recipeRepository.findAll();
    }

    @Override
    public Recipes getRecipeById(int recipeID){

        return recipeRepository.findById(recipeID).orElse(null);
    }

    @Override
    public Recipes addRecipe(Recipes recipe) {

        return recipeRepository.save(recipe);
    }

    @Override
    public Recipes updateRecipe(Recipes recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipes deleteRecipe(int recipeID) throws Exception {
        Recipes deletedRecipe = null;
        try {
            deletedRecipe = recipeRepository.findById(recipeID).orElse(null);
            if(deletedRecipe == null) {
                throw new Exception("recipe not available");
            }
            else {
                recipeRepository.deleteById(recipeID);
            }
        }
        catch(Exception ex) {
            throw ex;
        }
        return deletedRecipe;
    }

}
