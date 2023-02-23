package com.multiverse.api.springsecuritybackendmultiverseapi.recipes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
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
    private String user;




}
