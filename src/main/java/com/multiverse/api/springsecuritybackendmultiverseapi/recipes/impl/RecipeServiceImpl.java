package com.multiverse.api.springsecuritybackendmultiverseapi.recipes.impl;

import com.multiverse.api.springsecuritybackendmultiverseapi.recipes.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeBuilder recipeBuilder;

    @Override
    public List<Recipe> getAllRecipes() {
        return (List<Recipe>) recipeRepository.findAll();
    }

    @Override
    public Recipe getRecipeById(int recipeID){
        return recipeRepository.findById(recipeID).orElse(null);
    }

    @Override
    public Recipe addRecipe(HttpServletRequest token, RecipeRequest recipeRequest) {
        return recipeRepository.save(recipeBuilder.build(token,recipeRequest));
    }

    @Override
    @Transactional
    public Recipe updateRecipe(RecipeRequest recipeRequest, int recipeID) {
        Recipe updateRecipe = recipeRepository.findById(recipeID).orElseGet(Recipe::new);
        if(updateRecipe.getTitle() != null){
            updateRecipe.setTitle(recipeRequest.getTitle());
        }
        return updateRecipe;
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
