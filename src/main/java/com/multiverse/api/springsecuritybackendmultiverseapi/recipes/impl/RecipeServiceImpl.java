package com.multiverse.api.springsecuritybackendmultiverseapi.recipes.impl;

import com.multiverse.api.springsecuritybackendmultiverseapi.auth.Extract;
import com.multiverse.api.springsecuritybackendmultiverseapi.exception.CustomError;
import com.multiverse.api.springsecuritybackendmultiverseapi.recipes.*;
import com.multiverse.api.springsecuritybackendmultiverseapi.user.User;
import com.multiverse.api.springsecuritybackendmultiverseapi.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Extract extract;

    @Autowired
    private RecipeBuilder recipeBuilder;

    @Override
    public ResponseEntity<List<Recipe>> getAllRecipes(HttpServletRequest token)  throws CustomError{
        User user = userRepository.findByEmail(extract.emailFromJwt(token)).orElseThrow(()-> new CustomError("User not found"));
        return extract.listOfRecipe(user);
    }

    @Override
    public Recipe getRecipeById(int recipeID)throws CustomError{
        return recipeRepository.findById(recipeID).orElseThrow(()->new CustomError("Recipe not found"));
    }

    @Override
    public Recipe addRecipe(HttpServletRequest token, RecipeRequest recipeRequest) {
        return recipeRepository.save(recipeBuilder.build(token,recipeRequest));
    }

    @Override
    @Transactional
    public ResponseEntity<Recipe> updateRecipe(HttpServletRequest token, RecipeRequest recipeRequest, int recipeID) throws CustomError{
        Recipe updateRecipe = recipeRepository.findById(recipeID).orElseThrow(()->new CustomError("Recipe not found"));
        User user = userRepository.findByEmail(extract.emailFromJwt(token)).orElseThrow(()->new CustomError("User not found"));
        if(extract.getRole(user).equals("ADMIN") || updateRecipe.getCreateBy().equals(user.getEmail())){
            updateRecipe.setTitle(recipeRequest.getTitle());
            return ResponseEntity.ok().body(updateRecipe);
        }
        throw new CustomError("Admin not found or User not recipe owner");
    }

    @Override
    public String deleteRecipe(int recipeID) throws CustomError {
        Recipe recipe = recipeRepository.findById(recipeID).orElseThrow(()->new CustomError("Recipe not found"));
        recipeRepository.delete(recipe);
        return "Recipe deleted";
    }

}
