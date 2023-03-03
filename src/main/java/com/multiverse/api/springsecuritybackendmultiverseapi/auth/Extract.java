package com.multiverse.api.springsecuritybackendmultiverseapi.auth;

import com.multiverse.api.springsecuritybackendmultiverseapi.exception.CustomError;
import com.multiverse.api.springsecuritybackendmultiverseapi.recipes.Recipe;
import com.multiverse.api.springsecuritybackendmultiverseapi.recipes.RecipeRepository;
import com.multiverse.api.springsecuritybackendmultiverseapi.role.Role;
import com.multiverse.api.springsecuritybackendmultiverseapi.role.RoleRepository;
import com.multiverse.api.springsecuritybackendmultiverseapi.user.User;
import com.multiverse.api.springsecuritybackendmultiverseapi.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Component
public class Extract {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RoleRepository roleRepository;

    public String emailFromJwt(HttpServletRequest token){
        String authHeader = token.getHeader("Authorization");
        String jwtToken = authHeader.substring(7);
        return jwtService.extractUsername(jwtToken);
    }

    public ResponseEntity<List<User>> listOfUser(User user) throws CustomError{
        String roleName = getRole(user);
        if(roleName.equals("WORKER")){
            List<User> userList = new ArrayList<>();
            userList.add(userRepository.findById(user.getId()).orElseThrow(()->new CustomError("User not found")));
            return ResponseEntity.ok().body(userList);
        }
        if(roleName.equals("ADMIN")){
            return ResponseEntity.ok().body(userRepository.findAll());
        }
        throw new CustomError("Admin not found");
    }

    public ResponseEntity<List<Recipe>> listOfRecipe(User user) throws CustomError{
        String roleName = getRole(user);
        if(roleName.equals("WORKER")){
            return ResponseEntity.ok().body(user.getRecipes());
        }
        if(roleName.equals("ADMIN")){
            return ResponseEntity.ok().body((List<Recipe>) recipeRepository.findAll());
        }
        throw new CustomError("Admin not found");
    }

    public ResponseEntity<List<Role>> listOfRole(User user) throws CustomError{
        String roleName = getRole(user);
        if(roleName.equals("WORKER")){
            List<Role> roleList = new ArrayList<>();
            roleList.add(user.getRole());
            return ResponseEntity.ok().body(roleList);
        }
        if(roleName.equals("ADMIN")){ //the user exist?
                return ResponseEntity.ok().body((List<Role>) roleRepository.findAll());
        }
        throw new CustomError("Admin not found");
    }

    //if admin returns all roles. if grunt returns role user has
    public String getRole(User user){
        return user.getRole().getName();
    }
}
