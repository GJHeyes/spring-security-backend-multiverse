package com.multiverse.api.springsecuritybackendmultiverseapi.user;

import com.multiverse.api.springsecuritybackendmultiverseapi.recipes.Recipe;
import com.multiverse.api.springsecuritybackendmultiverseapi.recipes.RecipeService;
import com.multiverse.api.springsecuritybackendmultiverseapi.recipes.impl.RecipeServiceImpl;
import com.multiverse.api.springsecuritybackendmultiverseapi.user.impl.UserServiceImpl;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RecipeServiceImpl recipeService;

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int userID){
        User user = null;
        try {
            user = userService.getUserById(userID);
        }
        catch(Exception ex) {
            ex.getMessage();
        }

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<User> deleteUser(@RequestBody @NonNull UserRequest userRequest, @PathVariable("id") Integer userId){
        return userService.deleteUserById(userRequest, userId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> editUserEmail(UserRequest userRequest, @PathVariable("id") Integer userId){
        return userService.editUserEmailById(userRequest, userId);
    }

    @PostMapping()
    public ResponseEntity<User> addUser(UserRequest userRequest){
        return userService.addUser(userRequest);
    }

    @Transactional
    @PutMapping("/{id}/{recipeID}")
    public ResponseEntity<User> favouriteRecipe(@PathVariable("id") int userID, @PathVariable("recipeID") int recipeID) {
        User user = userService.getUserById(userID);
        Recipe recipe = recipeService.getRecipeById(recipeID);
        if (recipe == null){
            return ResponseEntity.badRequest().body(user);
        }
        user.getRecipes().add(recipe);
        return ResponseEntity.ok().body(user);
    }


}
