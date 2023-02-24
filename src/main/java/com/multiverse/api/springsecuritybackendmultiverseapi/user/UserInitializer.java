package com.multiverse.api.springsecuritybackendmultiverseapi.user;

import com.multiverse.api.springsecuritybackendmultiverseapi.recipes.RecipeRepository;
import com.multiverse.api.springsecuritybackendmultiverseapi.recipes.Recipes;
import com.multiverse.api.springsecuritybackendmultiverseapi.role.RoleRepository;
import com.multiverse.api.springsecuritybackendmultiverseapi.role.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.Set;

@Component
@Transactional
public class UserInitializer implements ApplicationRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        User admin = User.builder()
                .email("admin")
                .firstname("admin")
                .lastname("admin")
                .password("admin")
                .build();

        Roles role = Roles.builder()
                .name("ADMIN")
                .description("ADMIN")
                .build();

        Recipes recipe = Recipes.builder()
                        .title("test")
                        .build();

        userRepository.save(admin);
        roleRepository.save(role);
        recipeRepository.save(recipe);

        admin = userRepository.findByEmail(admin.getEmail()).orElseGet(User::new);

        role = roleRepository.findById(1).orElseGet(Roles::new);

        admin.setRole(role);

    }
}
