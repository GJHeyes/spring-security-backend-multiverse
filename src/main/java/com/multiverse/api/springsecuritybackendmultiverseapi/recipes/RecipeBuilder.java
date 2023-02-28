package com.multiverse.api.springsecuritybackendmultiverseapi.recipes;

import com.multiverse.api.springsecuritybackendmultiverseapi.auth.Extract;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RecipeBuilder {

    @Autowired
    private Extract extract;

    public Recipe build(HttpServletRequest token, RecipeRequest recipeRequest){
        return Recipe.builder()
                .title(recipeRequest.getTitle())
                .category(recipeRequest.getCategory())
                .createBy(extract.emailFromJwt(token))
                .build();
    }
}
