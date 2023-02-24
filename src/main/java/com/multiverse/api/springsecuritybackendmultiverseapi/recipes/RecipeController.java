package com.multiverse.api.springsecuritybackendmultiverseapi.recipes;

import com.multiverse.api.springsecuritybackendmultiverseapi.recipes.impl.RecipeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe){
       Recipe recipes = null;
        try {
            recipes = recipeService.addRecipe(recipe);
        }
        catch(Exception ex) {
            ex.getMessage();
        }
        return new ResponseEntity<Recipe>(recipes, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@RequestBody Recipe recipe, @PathVariable("id") int recipeID){
            Recipe recipeRequest = recipeService.getRecipeById(recipeID);
            if (recipeRequest != null) {
                return ResponseEntity.ok().body (recipeService.updateRecipe(recipe, recipeID));
            }
            return ResponseEntity.badRequest().body(recipe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Recipe> addOrUpdate(@PathVariable("id") int recipeID){
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
