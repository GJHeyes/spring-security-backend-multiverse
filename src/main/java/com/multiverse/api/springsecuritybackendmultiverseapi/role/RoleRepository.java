package com.multiverse.api.springsecuritybackendmultiverseapi.role;

import com.multiverse.api.springsecuritybackendmultiverseapi.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findByName(String name);//takes a string called "name"

    //this is returning a role based on the name
}


//storing the data in the database
//add and call the data and the application