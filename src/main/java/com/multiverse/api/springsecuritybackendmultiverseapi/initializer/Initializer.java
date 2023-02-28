package com.multiverse.api.springsecuritybackendmultiverseapi.initializer;

import com.multiverse.api.springsecuritybackendmultiverseapi.recipes.RecipeRepository;
import com.multiverse.api.springsecuritybackendmultiverseapi.recipes.Recipe;
import com.multiverse.api.springsecuritybackendmultiverseapi.role.RoleRepository;
import com.multiverse.api.springsecuritybackendmultiverseapi.role.Role;
import com.multiverse.api.springsecuritybackendmultiverseapi.user.User;
import com.multiverse.api.springsecuritybackendmultiverseapi.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Component
public class Initializer implements ApplicationRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {

        User admin = User.builder()
                .email("Eugene.Krabs@KrustyKrab.com")
                .firstname("Eugene")
                .lastname("Krabs")
                .password("$2a$10$j3EhVEMgUilPCJ5vW1cbruKe34rbH7Ib.ps.fyZuhjKEUqm0HoZAO")
                .recipes(new ArrayList<>())
                .build();

        User grunt = User.builder()
                .email("SpongeBob.SquarePants@KrustyKrab.com")
                .firstname("SpongeBob")
                .lastname("SquarePants")
                .password("$2a$10$wG60IVZTZHo17B8Wv9zsquHkknSwPrcAUQMj0XpfOv7npalMIwo52")
                .recipes(new ArrayList<>())
                .build();

        Role adminRole = Role.builder()
                .name("ADMIN")
                .description("ADMIN")
                .responsibilities("Able to access all data")
                .build();

        Role gruntRole = Role.builder()
                .name("WORKER")
                .description("WORKER")
                .responsibilities("Able to access their own data")
                .build();

        Recipe krabbyPatty = Recipe.builder()
                .title("Krabby Patty")
                .category("Burger")
                .createBy("Eugene.Krabs@KrustyKrab.com")
                .build();

        Recipe kelpFries = Recipe.builder()
                .title("Kelp Fries")
                .category("Fries")
                .createBy("Eugene.Krabs@KrustyKrab.com")
                .build();

        Recipe seaweed = Recipe.builder()
                .title("Seaweed")
                .category("Sides")
                .createBy("Eugene.Krabs@KrustyKrab.com")
                .build();

        Recipe secretSauce = Recipe.builder()
                .title("Secret Sauce")
                .category("Sauces")
                .createBy("SpongeBob.SquarePants@KrustyKrab.com")
                .build();

        Recipe ketchup = Recipe.builder()
                .title("Ketchup")
                .category("Sauces")
                .createBy("Eugene.Krabs@KrustyKrab.com")
                .build();

        Recipe beyondKrabbyPatty = Recipe.builder()
                .title("Beyond Krabby Patty - Vegan")
                .category("Burger")
                .createBy("SpongeBob.SquarePants@KrustyKrab.com")
                .build();

        Recipe mayo = Recipe.builder()
                .title("Mayo")
                .category("Sauces")
                .createBy("Eugene.Krabs@KrustyKrab.com")
                .build();

        Recipe coralMash = Recipe.builder()
                .title("Coral Mash")
                .category("Sides")
                .createBy("Eugene.Krabs@KrustyKrab.com")
                .build();

        Recipe krabbSticks = Recipe.builder()
                .title("Krabb Sticks")
                .category("Sides")
                .createBy("Eugene.Krabs@KrustyKrab.com")
                .build();

        Recipe escargot = Recipe.builder()
                .title("Escargot")
                .category("Sides")
                .createBy("Eugene.Krabs@KrustyKrab.com")
                .build();

        Recipe pineappleBurger = Recipe.builder()
                .title("Pineapple Burger")
                .category("Burger")
                .createBy("SpongeBob.SquarePants@KrustyKrab.com")
                .build();

        Recipe pineappleFrittes = Recipe.builder()
                .title("Pineapple Frittes")
                .category("Fries")
                .createBy("SpongeBob.SquarePants@KrustyKrab.com")
                .build();


        userRepository.save(admin);
        userRepository.save(grunt);
        roleRepository.save(adminRole);
        roleRepository.save(gruntRole);
        recipeRepository.save(krabbyPatty);
        recipeRepository.save(kelpFries);
        recipeRepository.save(seaweed);
        recipeRepository.save(secretSauce);
        recipeRepository.save(ketchup);
        recipeRepository.save(beyondKrabbyPatty);
        recipeRepository.save(mayo);
        recipeRepository.save(coralMash);
        recipeRepository.save(krabbSticks);
        recipeRepository.save(escargot);
        recipeRepository.save(pineappleBurger);
        recipeRepository.save(pineappleFrittes);


        admin = userRepository.findById(1).orElseGet(User::new);

        adminRole = roleRepository.findById(1).orElseGet(Role::new);

        admin.setRole(adminRole);

        grunt = userRepository.findById(2).orElseGet(User::new);

        gruntRole = roleRepository.findById(2).orElseGet(Role::new);

        grunt.setRole(gruntRole);
        admin.getRecipes().add(krabbyPatty);
        admin.getRecipes().add(kelpFries);

    }
}
