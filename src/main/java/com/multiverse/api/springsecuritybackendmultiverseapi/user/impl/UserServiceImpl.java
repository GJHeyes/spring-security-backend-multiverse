package com.multiverse.api.springsecuritybackendmultiverseapi.user.impl;

import com.multiverse.api.springsecuritybackendmultiverseapi.auth.Extract;
import com.multiverse.api.springsecuritybackendmultiverseapi.role.Role;
import com.multiverse.api.springsecuritybackendmultiverseapi.role.RoleRepository;
import com.multiverse.api.springsecuritybackendmultiverseapi.user.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserBuilder userBuilder;

    @Autowired
    private Extract extract;

    @Override
    public ResponseEntity<List<User>> getAllUsers(HttpServletRequest token) {
        User user = userRepository.findByEmail(extract.emailFromJwt(token)).orElseGet(User::new);
        return extract.listOfUser(user);
    }

    @Override
    public User getUserById(int userID){

        return userRepository.findById(userID).orElse(null);
    }

    @Override
    public ResponseEntity<String> deleteUserById(HttpServletRequest token, Integer userId) {

        Optional<User> optionalUser =  userRepository.findById(userId);
        User user = optionalUser.orElseGet(User::new);
        if(optionalUser.isPresent()){
            userRepository.delete(user);
            return ResponseEntity.ok().body("User deleted");
        }
        return ResponseEntity.badRequest().body("User does not exist");
    }

    @Override
    @Transactional
    public ResponseEntity<String> editUserById(HttpServletRequest token, UserRequest userRequest, Integer userId) {
        User user = userRepository.findById(userId).orElseGet(User::new);
        if(user.getId() != null){
            if(userRequest.getEmail() != null){
                user.setEmail(userRequest.getEmail());
            }
            if(userRequest.getPassword() != null){
                user.setPassword(userRequest.getPassword());
            }
            return ResponseEntity.ok().body(user.getEmail());
        }
        return ResponseEntity.badRequest().body("User not found");
    }

    @Override
    public ResponseEntity<User> addUser(HttpServletRequest token, UserRequest userRequest) {
        return ResponseEntity.ok().body(userRepository.save(userBuilder.build(userRequest)));
    }

    @Override
    @Transactional
    public ResponseEntity<String> workerToAdmin(HttpServletRequest token, Integer userId) {
        User admin = userRepository.findByEmail(extract.emailFromJwt(token)).orElseGet(User::new);
        User user = userRepository.findById(userId).orElseGet(User::new);
        Role role = roleRepository.findByName("ADMIN").orElseGet(Role::new);
        if(extract.getRole(admin).equals("ADMIN") && user.getId() != null){
            user.setRole(role);
            return ResponseEntity.ok().body(String.format("Email: %s, Role: %s", user.getEmail(),user.getRole().getName()));
        }
        return ResponseEntity.badRequest().body("Either requester is not an Admin or User does not exist");
    }
}
