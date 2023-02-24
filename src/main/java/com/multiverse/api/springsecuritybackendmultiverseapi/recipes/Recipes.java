package com.multiverse.api.springsecuritybackendmultiverseapi.recipes;

import com.multiverse.api.springsecuritybackendmultiverseapi.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Recipes {

    @Id
    @GeneratedValue
    @Column(name="recipe_id")
    private int recipeID;

    @Column(name="title")
    private String title;


    @Column(name="category")
    private String category;

    //needs to be from the User class
    @Column(name="user")
    @ManyToMany(mappedBy = "recipes", fetch = FetchType.LAZY)
    private Set<User> users;

}
