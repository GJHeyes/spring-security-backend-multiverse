package com.multiverse.api.springsecuritybackendmultiverseapi.Role;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="role")
public class Roles {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")

        private int roleId;
        private String name;
        private String description;


    //every role has a name, ID and a list of users

@OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<Roles> roles;
    private Set<String> responsibilities;

    //list of users and responsibilities

    //ADMIN, GRUNT
}





