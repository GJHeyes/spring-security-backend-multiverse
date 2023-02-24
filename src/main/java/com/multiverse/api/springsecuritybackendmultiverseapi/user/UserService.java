package com.multiverse.api.springsecuritybackendmultiverseapi.user;

import org.springframework.http.ResponseEntity;

import java.util.List;


public interface UserService {

    public ResponseEntity<List<User>> getAllUsers();

    public ResponseEntity<User> deleteUserById(UserRequest userRequest, Integer userId);

    public ResponseEntity<User> editUserEmailById(UserRequest userRequest, Integer userId);

    public ResponseEntity<User> addUser(UserRequest userRequest);
}
