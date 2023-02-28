package com.multiverse.api.springsecuritybackendmultiverseapi.recipes;

import com.multiverse.api.springsecuritybackendmultiverseapi.recipes.impl.RecipeServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeServiceImpl recipeService;

    @GetMapping()
    public ResponseEntity<List<Recipe>> getAllRecipes(){
        List<Recipe> recipes = null;
        try {
            recipes = recipeService.getAllRecipes();
        }
        catch(Exception ex) {
            ex.getMessage();
        }

        return new ResponseEntity<List<Recipe>>(recipes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipesById(@PathVariable("id") int recipeID){
        Recipe recipe = null;
        try {
            recipe = recipeService.getRecipeById(recipeID);
        }
        catch(Exception ex) {
            ex.getMessage();
        }

        return new ResponseEntity<Recipe>(recipe, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Recipe> addRecipe(@NonNull HttpServletRequest token, @RequestBody  RecipeRequest recipeRequest){
       Recipe recipes = null;
        try {
            recipes = recipeService.addRecipe(token, recipeRequest);
        }
        catch(Exception ex) {
            ex.getMessage();
        }
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@RequestBody RecipeRequest recipeRequest, @PathVariable("id") int recipeID){
            Recipe optionalRequest = recipeService.getRecipeById(recipeID);
            if (optionalRequest != null) {
                return ResponseEntity.ok().body (recipeService.updateRecipe(recipeRequest, recipeID));
            }
            return ResponseEntity.badRequest().body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable("id") int recipeID){
        Recipe recipe = null;
        try {
            recipe = recipeService.deleteRecipe(recipeID);
        }
        catch(Exception ex) {
            ex.getMessage();
        }
        return new ResponseEntity<Recipe>(recipe, HttpStatus.OK);
    }
}
