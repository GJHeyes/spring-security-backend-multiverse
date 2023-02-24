package com.multiverse.api.springsecuritybackendmultiverseapi.recipes.impl;

import com.multiverse.api.springsecuritybackendmultiverseapi.recipes.RecipeRepository;
import com.multiverse.api.springsecuritybackendmultiverseapi.recipes.RecipeService;
import com.multiverse.api.springsecuritybackendmultiverseapi.recipes.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public List<Recipe> getAllRecipes() {
        return (List<Recipe>) recipeRepository.findAll();
    }

    @Override
    public Recipe getRecipeById(int recipeID){

        return recipeRepository.findById(recipeID).orElse(null);
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {

        return recipeRepository.save(recipe);
    }

    @Override
    @Transactional
    public Recipe updateRecipe(Recipe recipe, int recipeID) {
        Recipe recipeRequest = recipeRepository.findById(recipeID).orElseGet(Recipe::new);
        recipeRequest.setTitle(recipe.getTitle());
        return recipeRequest;
    }

    @Override
    public Recipe deleteRecipe(int recipeID) throws Exception {
        Recipe deletedRecipe = null;
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
