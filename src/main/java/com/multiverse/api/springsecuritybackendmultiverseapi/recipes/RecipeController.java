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

    @GetMapping("/{id}")
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

    @PostMapping()
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

    @PutMapping("/{id}")
    public ResponseEntity<Recipes> updateRecipe(@RequestBody Recipes recipe, @PathVariable("id") int recipeID){
            Recipes recipeRequest = recipeService.getRecipeById(recipeID);
            if (recipeRequest != null) {
                return ResponseEntity.ok().body (recipeService.updateRecipe(recipe, recipeID));
            }
            return ResponseEntity.badRequest().body(recipe);
    }

    @DeleteMapping("/{id}")
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
