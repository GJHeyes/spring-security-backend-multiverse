package com.multiverse.api.springsecuritybackendmultiverseapi.role;

import org.springframework.data.repository.CrudRepository;

public interface RoleRepo extends CrudRepository<Roles, Integer> {
}

//storing the data in the database
//add and call the data and the application