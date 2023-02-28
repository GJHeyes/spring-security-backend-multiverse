package com.multiverse.api.springsecuritybackendmultiverseapi.user;

import com.multiverse.api.springsecuritybackendmultiverseapi.recipes.RecipeRepository;
import com.multiverse.api.springsecuritybackendmultiverseapi.recipes.Recipe;
import com.multiverse.api.springsecuritybackendmultiverseapi.role.RoleRepository;
import com.multiverse.api.springsecuritybackendmultiverseapi.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserInitializer implements ApplicationRunner {
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
                .build();

        Recipe kelpFries = Recipe.builder()
                .title("Kelp Fries")
                .category("Fries")
                .build();


        userRepository.save(admin);
        userRepository.save(grunt);
        roleRepository.save(adminRole);
        roleRepository.save(gruntRole);
        recipeRepository.save(krabbyPatty);
        recipeRepository.save(kelpFries);

        admin = userRepository.findById(1).orElseGet(User::new);

        adminRole = roleRepository.findById(1).orElseGet(Role::new);

        admin.setRole(adminRole);

        //admin.getRecipes().add(krabbyPatty);

    }
}
