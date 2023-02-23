package com.multiverse.api.springsecuritybackendmultiverseapi.recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/recipes")
    public ResponseEntity<List<Recipes>> getAllRecipes(){
        List<Recipes> recipes = null;
        try {
            recipes = recipeService.getAllRecipes();
        }
        catch(Exception ex) {
            ex.getMessage();
        }

        return new ResponseEntity<List<Recipes>>(recipes, HttpStatus.OK);
    }

    @GetMapping("/recipes/{id}")
    public ResponseEntity<Recipes> getRecipesById(@PathVariable("id") int recipeID){
        Recipes recipes = null;
        try {
            recipes = recipeService.getRecipeById(recipeID);
        }
        catch(Exception ex) {
            ex.getMessage();
        }

        return new ResponseEntity<Recipes>(recipes, HttpStatus.OK);
    }

    @PostMapping ("/recipes")
    public ResponseEntity<Recipes> addRecipe(@RequestBody Recipes recipe){
       Recipes recipes = null;
        try {
            recipes = recipeService.addRecipe(recipe);
        }
        catch(Exception ex) {
            ex.getMessage();
        }
        return new ResponseEntity<Recipes>(recipes, HttpStatus.OK);
    }

    @PutMapping ("/recipes/{id}")
    public ResponseEntity<Recipes> updateRecipe(@RequestBody Recipes recipe, @PathVariable("id") int recipeID){
        Recipes recipes = null;
        try {
            recipes = recipeService.getRecipeById(recipeID);
            if (recipes != null) {
                recipes = recipeService.updateRecipe(recipe);
            }
            recipes = recipeService.updateRecipe(recipe);
        }
        catch(Exception ex) {
            ex.getMessage();
        }
        return new ResponseEntity<Recipes>(recipes, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Recipes> addOrUpdate(@PathVariable("id") int recipeID){
        Recipes recipes = null;
        try {
            recipes = recipeService.deleteRecipe(recipeID);
        }
        catch(Exception ex) {
            ex.getMessage();
        }
        return new ResponseEntity<Recipes>(recipes, HttpStatus.OK);
    }
}
