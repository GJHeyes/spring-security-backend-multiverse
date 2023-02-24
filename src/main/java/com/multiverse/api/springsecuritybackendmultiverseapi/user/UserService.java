package com.multiverse.api.springsecuritybackendmultiverseapi.user;

import org.springframework.http.ResponseEntity;

import java.util.List;


public interface UserService {

    public ResponseEntity<List<Users>> getAllUsers();

    public ResponseEntity<Users> deleteUserById(UserRequest userRequest, Integer userId);

    public ResponseEntity<Users> editUserEmailById(UserRequest userRequest, Integer userId);

    public ResponseEntity<Users> addUser(UserRequest userRequest);
}
