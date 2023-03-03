package com.multiverse.api.springsecuritybackendmultiverseapi.user;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface UserService {

    public ResponseEntity<List<User>> getAllUsers(HttpServletRequest token);

    public ResponseEntity<User> getUserById(int UserID);

    public ResponseEntity<String> deleteUserById(HttpServletRequest token, Integer userId);

    public ResponseEntity<User> editUserById(HttpServletRequest token, UserRequest userRequest, Integer userId);

    public ResponseEntity<User> addUser(HttpServletRequest token, UserRequest userRequest);

    public ResponseEntity<User> workerToAdmin(HttpServletRequest token, Integer userId);

    public ResponseEntity<User> addRecipeToUser(HttpServletRequest token,Integer userId, Integer recipeID);
}
