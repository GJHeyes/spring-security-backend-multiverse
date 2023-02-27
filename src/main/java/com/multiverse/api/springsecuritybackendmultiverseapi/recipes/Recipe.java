package com.multiverse.api.springsecuritybackendmultiverseapi.recipes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.multiverse.api.springsecuritybackendmultiverseapi.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Recipe {

    @Id
    @GeneratedValue
    @Column(name="recipe_id")
    private int recipeID;

    @Column(name="title")
    private String title;


    @Column(name="category")
    private String category;

    //needs to be from the User class
    @JsonBackReference
    @Column(name="users")
    @ManyToMany(mappedBy = "recipes")
    private List<User> users;

}
