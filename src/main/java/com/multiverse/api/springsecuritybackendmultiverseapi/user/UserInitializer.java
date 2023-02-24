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

        Role role = Role.builder()
                .name("ADMIN")
                .description("ADMIN")
                .build();

        Recipe recipe = Recipe.builder()
                        .title("test")
                        .build();

        userRepository.save(admin);
        roleRepository.save(role);
        recipeRepository.save(recipe);

        admin = userRepository.findById(1).orElseGet(User::new);

        role = roleRepository.findById(1).orElseGet(Role::new);

        admin.setRole(role);

    }
}
