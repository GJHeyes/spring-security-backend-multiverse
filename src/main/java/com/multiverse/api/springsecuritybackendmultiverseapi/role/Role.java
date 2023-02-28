package com.multiverse.api.springsecuritybackendmultiverseapi.role;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.multiverse.api.springsecuritybackendmultiverseapi.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Table(name="role")
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
        private int roleId;
        private String name;
        private String description;


    //every role has a name, ID and a list of users


    @JsonBackReference
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<User> users;
    private String responsibilities;

    //list of users and responsibilities

    //ADMIN, GRUNT
}





