package com.multiverse.api.springsecuritybackendmultiverseapi.user;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface UserService {

    public ResponseEntity<List<User>> getAllUsers(HttpServletRequest token);

    public ResponseEntity<User> deleteUserById(HttpServletRequest token, UserRequest userRequest, Integer userId);

    public ResponseEntity<User> editUserEmailById(HttpServletRequest token, UserRequest userRequest, Integer userId);

    public ResponseEntity<User> addUser(HttpServletRequest token, UserRequest userRequest);
}
