package com.multiverse.api.springsecuritybackendmultiverseapi.user.impl;

import com.multiverse.api.springsecuritybackendmultiverseapi.auth.Extract;
import com.multiverse.api.springsecuritybackendmultiverseapi.exception.CustomError;
import com.multiverse.api.springsecuritybackendmultiverseapi.recipes.Recipe;
import com.multiverse.api.springsecuritybackendmultiverseapi.recipes.RecipeRepository;
import com.multiverse.api.springsecuritybackendmultiverseapi.role.Role;
import com.multiverse.api.springsecuritybackendmultiverseapi.role.RoleRepository;
import com.multiverse.api.springsecuritybackendmultiverseapi.user.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final RecipeRepository recipeRepository;
    private final UserBuilder userBuilder;
    private final Extract extract;

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
        String email =  extract.emailFromJwt(token);
        User user =  userRepository.findById(userId).orElseThrow(()-> new CustomError("User not found"));
        User admin = userRepository.findByEmail(email).orElseThrow(()-> new CustomError("Invalid bearer token"));
        boolean correctUser = user.getEmail().equals(email);
        if(correctUser|| admin.getRole().getName().equals("ADMIN")){
            userRepository.delete(user);
            return ResponseEntity.ok().body("User deleted");
        }
        throw new CustomError("Incorrect user, access denied");
    }

    @Override
    @Transactional
    public ResponseEntity<User> editUserById(HttpServletRequest token, UserRequest userRequest, Integer userId) throws CustomError {
        String email =  extract.emailFromJwt(token);
        User user = userRepository.findById(userId).orElseThrow(()-> new CustomError("User not found"));
        User admin = userRepository.findByEmail(email).orElseThrow(()-> new CustomError("Invalid Bearer Token"));
        boolean correctUser = user.getEmail().equals(email);
        if(userRequest != null && (correctUser || admin.getRole().getName().equals("ADMIN"))){
            if(userRequest.getEmail() != null){
                user.setEmail(userRequest.getEmail());
            }
            if(userRequest.getPassword() != null){
                user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            }
            return ResponseEntity.ok().body(user);
        }
        throw new CustomError("Incorrect User, or no data to update");
    }

    //Not relevent
    @Override
    public ResponseEntity<User> addUser(HttpServletRequest token, UserRequest userRequest) {
        return ResponseEntity.ok().body(userRepository.save(userBuilder.build(userRequest)));
    }

    @Override
    @Transactional
    public ResponseEntity<User> workerToAdmin(HttpServletRequest token, Integer userId) throws CustomError{
        User admin = userRepository.findByEmail(extract.emailFromJwt(token)).orElseThrow(()-> new CustomError("Admin doesn't exist"));
        User user = userRepository.findById(userId).orElseThrow(()-> new CustomError("User not found"));
        Role role = roleRepository.findByName("ADMIN").orElseThrow(()-> new CustomError("Admin not found"));
        if(extract.getRole(admin).equals("ADMIN")){
            user.setRole(role);
            return ResponseEntity.ok().body(user);
        }
        throw new CustomError("User not admin, access denied");
    }

    @Override
    @Transactional
    public ResponseEntity<User> addRecipeToUser(HttpServletRequest token, Integer userId, Integer recipeID) throws CustomError {
        User admin = userRepository.findByEmail(extract.emailFromJwt(token)).orElseThrow(()-> new CustomError("Admin doesn't exist"));
        User user = userRepository.findById(userId).orElseThrow(()-> new CustomError("User not found"));
        Recipe recipe = recipeRepository.findById(recipeID).orElseThrow(()-> new CustomError("Recipe not found"));
        if(extract.getRole(admin).equals("ADMIN")) {
            user.getRecipes().add(recipe);
            return ResponseEntity.ok().body(user);
        }
        throw new CustomError("User not admin, access denied");
    }
}
