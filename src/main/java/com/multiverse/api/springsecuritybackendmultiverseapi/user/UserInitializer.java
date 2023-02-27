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
                .email("admin")
                .firstname("admin")
                .lastname("admin")
                .password("$2a$10$j3EhVEMgUilPCJ5vW1cbruKe34rbH7Ib.ps.fyZuhjKEUqm0HoZAO")
                .recipes(new HashSet<>())
                .build();

        Role adminRole = Role.builder()
                .name("ADMIN")
                .description("ADMIN")
                .responsibilities("Able to access all data")
                .build();

        Role gruntRole = Role.builder()
                .name("GRUNT")
                .description("GRUNT")
                .responsibilities("Able to access their own data")
                .build();

        Recipe recipe = Recipe.builder()
                        .title("test")
                        .build();

        admin.getRecipes().add(recipe);

        userRepository.save(admin);
        roleRepository.save(adminRole);
        recipeRepository.save(recipe);

        admin = userRepository.findById(1).orElseGet(User::new);

        adminRole = roleRepository.findById(1).orElseGet(Role::new);

        admin.setRole(adminRole);

    }
}
