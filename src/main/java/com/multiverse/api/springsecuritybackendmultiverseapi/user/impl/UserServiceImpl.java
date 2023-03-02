package com.multiverse.api.springsecuritybackendmultiverseapi.user.impl;

import com.multiverse.api.springsecuritybackendmultiverseapi.auth.Extract;
import com.multiverse.api.springsecuritybackendmultiverseapi.exception.CustomError;
import com.multiverse.api.springsecuritybackendmultiverseapi.recipes.Recipe;
import com.multiverse.api.springsecuritybackendmultiverseapi.recipes.RecipeRepository;
import com.multiverse.api.springsecuritybackendmultiverseapi.role.Role;
import com.multiverse.api.springsecuritybackendmultiverseapi.role.RoleRepository;
import com.multiverse.api.springsecuritybackendmultiverseapi.user.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserBuilder userBuilder;

    @Autowired
    private Extract extract;

    @Override
    public ResponseEntity<List<User>> getAllUsers(HttpServletRequest token) throws CustomError {
        User user = userRepository.findByEmail(extract.emailFromJwt(token)).orElseThrow(()-> new CustomError("User not found"));
        return extract.listOfUser(user);
    }

    @Override
    public ResponseEntity<User> getUserById(int userID) throws CustomError{
        User user = userRepository.findById(userID).orElseThrow(()-> new CustomError("User not found"));
        return ResponseEntity.ok().body(user);
    }

    @Override
    public ResponseEntity<String> deleteUserById(HttpServletRequest token, Integer userId) throws CustomError {
        User user =  userRepository.findById(userId).orElseThrow(()-> new CustomError("User not found"));
        userRepository.delete(user);
        return ResponseEntity.ok().body("User deleted");
    }

    @Override
    @Transactional
    public ResponseEntity<String> editUserById(HttpServletRequest token, UserRequest userRequest, Integer userId) throws CustomError {
        String email =  extract.emailFromJwt(token);
        User user = userRepository.findById(userId).orElseThrow(()-> new CustomError("User not found"));
        boolean correctUser = user.getEmail().equals(email);
        if(userRequest != null && correctUser){
            if(userRequest.getEmail() != null){
                user.setEmail(userRequest.getEmail());
            }
            if(userRequest.getPassword() != null){
                user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            }
            return ResponseEntity.ok().body(user.getEmail());
        }
        return ResponseEntity.badRequest().body("Incorrect User, or no data to update");
    }

    //Not relevent
    @Override
    public ResponseEntity<User> addUser(HttpServletRequest token, UserRequest userRequest) {
        return ResponseEntity.ok().body(userRepository.save(userBuilder.build(userRequest)));
    }

    @Override
    @Transactional
    public ResponseEntity<String> workerToAdmin(HttpServletRequest token, Integer userId) throws CustomError{
        User admin = userRepository.findByEmail(extract.emailFromJwt(token)).orElseThrow(()-> new CustomError("Admin doesn't exist"));
        User user = userRepository.findById(userId).orElseThrow(()-> new CustomError("User not found"));
        Role role = roleRepository.findByName("ADMIN").orElseThrow(()-> new CustomError("Admin not found"));
        if(extract.getRole(admin).equals("ADMIN")){
            user.setRole(role);
            return ResponseEntity.ok().body(String.format("Email: %s, Role: %s", user.getEmail(),user.getRole().getName()));
        }
        return ResponseEntity.badRequest().body("Unknown Error");
    }

    @Override
    @Transactional
    public ResponseEntity<String> addRecipeToUser(Integer userId, Integer recipeID) throws CustomError {
        User user = userRepository.findById(userId).orElseThrow(()-> new CustomError("User not found"));
        Recipe recipe = recipeRepository.findById(recipeID).orElseThrow(()-> new CustomError("Recipe not found"));
        user.getRecipes().add(recipe);
        return ResponseEntity.ok().body(user.getRecipes().get(user.getRecipes().size()-1).getTitle());
    }
}
