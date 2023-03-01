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
    public ResponseEntity<User> deleteUserById(HttpServletRequest token, UserRequest userRequest, Integer userId) {

        Optional<User> optionalUser =  userRepository.findById(userId);
        User user = optionalUser.orElseGet(User::new);
        if(optionalUser.isPresent()){
            userRepository.delete(user);
            return ResponseEntity.ok().body(user);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @Override
    @Transactional
    public ResponseEntity<User> editUserEmailById(HttpServletRequest token, UserRequest userRequest, Integer userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = userRepository.findById(userId).orElseGet(User::new);
        if(optionalUser.isPresent()){
            user.setEmail(userRequest.getEmail());
            return ResponseEntity.ok().body(user);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @Override
    public ResponseEntity<User> addUser(HttpServletRequest token, UserRequest userRequest) {
        UserBuilder userBuilder = new UserBuilder();
        return ResponseEntity.ok().body(userRepository.save(userBuilder.build(userRequest)));
    }

    @Override
    @Transactional
    public ResponseEntity<User> workerToAdmin(HttpServletRequest token, Integer userId) {
        User admin = userRepository.findByEmail(extract.emailFromJwt(token)).orElseGet(User::new);
        User user = userRepository.findById(userId).orElseGet(User::new);
        Role role = roleRepository.findByName("ADMIN").orElseGet(Role::new);
        if(extract.getRole(admin).equals("ADMIN")){
            user.setRole(role);
            return ResponseEntity.ok().body(user);
        }
        return ResponseEntity.badRequest().body(null);
    }
}
