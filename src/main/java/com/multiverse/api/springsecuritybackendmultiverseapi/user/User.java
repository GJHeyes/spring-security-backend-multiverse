package com.multiverse.api.springsecuritybackendmultiverseapi.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    private String email;

    private String firstname;

    private String lastname;

    private String password;

    private Role role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "recipe_user",
    joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "recipe_id", referencedColumnName = "id"))
    private Set<Recipe> recipes;
}
