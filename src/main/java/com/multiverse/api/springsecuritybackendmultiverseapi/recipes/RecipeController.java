package com.multiverse.api.springsecuritybackendmultiverseapi.recipes;

import com.multiverse.api.springsecuritybackendmultiverseapi.recipes.impl.RecipeServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeServiceImpl recipeService;

    @GetMapping()
    public ResponseEntity<List<Recipe>> getAllRecipes(HttpServletRequest token){
        return recipeService.getAllRecipes(token);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipesById(@PathVariable("id") int recipeID){
        return new ResponseEntity<>(recipeService.getRecipeById(recipeID), HttpStatus.OK);
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
    public ResponseEntity<Recipe> updateRecipe(@NonNull HttpServletRequest token, @RequestBody RecipeRequest recipeRequest, @PathVariable("id") int recipeID){
        return recipeService.updateRecipe(token,recipeRequest, recipeID);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable("id") int recipeID){
        return ResponseEntity.ok().body(recipeService.deleteRecipe(recipeID));
    }
}
