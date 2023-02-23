package com.multiverse.api.springsecuritybackendmultiverseapi.user;

import org.springframework.http.ResponseEntity;

import java.util.List;


public interface UserService {

    public ResponseEntity<List<Users>> getAllUsers();

    public ResponseEntity<Users> deleteUserByUsername(UserRequest userRequest);

    public ResponseEntity<Users> editUserEmail(UserRequest userRequest);

    public ResponseEntity<Users> addUser(UserRequest userRequest);
}
