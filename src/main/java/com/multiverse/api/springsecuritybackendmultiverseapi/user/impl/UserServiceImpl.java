package com.multiverse.api.springsecuritybackendmultiverseapi.user.impl;

import com.multiverse.api.springsecuritybackendmultiverseapi.auth.ExtractEmail;
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
    private ExtractEmail extractEmail;

    @Override
    public ResponseEntity<List<User>> getAllUsers(HttpServletRequest token) {
        User user = userRepository.findByEmail(extractEmail.fromJwt(token)).orElseGet(User::new);
        if(user.getId() != null){
            if(user.getRole().getName().equals("GRUNT")){
                List<User> userList = new ArrayList<>();
                userList.add(userRepository.findById(user.getId()).orElseGet(User::new));
                return ResponseEntity.ok().body(userList);
            }
            return ResponseEntity.ok().body(userRepository.findAll());
        }
        return ResponseEntity.ok().body(new ArrayList<>());
    }

    @Override
    public ResponseEntity<User> deleteUserById(HttpServletRequest token, UserRequest userRequest, Integer userId) {

        Optional<User> optionalUser =  userRepository.findById(userId);
        User user = optionalUser.orElseGet(User::new);
        if(optionalUser.isPresent()){
            userRepository.delete(user);
            return ResponseEntity.ok().body(user);
        }
        return ResponseEntity.badRequest().body(user);
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
        return ResponseEntity.badRequest().body(user);
    }

    @Override
    public ResponseEntity<User> addUser(HttpServletRequest token, UserRequest userRequest) {
        UserBuilder userBuilder = new UserBuilder();
        return ResponseEntity.ok().body(userRepository.save(userBuilder.build(userRequest)));
    }
}
