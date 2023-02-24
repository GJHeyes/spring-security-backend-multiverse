package com.multiverse.api.springsecuritybackendmultiverseapi.user.impl;

import com.multiverse.api.springsecuritybackendmultiverseapi.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<List<Users>> getAllUsers() {
        return ResponseEntity.ok().body(userRepository.findAll());
    }

    @Override
    public ResponseEntity<Users> deleteUserById(UserRequest userRequest, Integer userId) {

        Optional<Users> optionalUser =  userRepository.findById(userId);
        Users users = optionalUser.orElseGet(Users::new);
        if(optionalUser.isPresent()){
            userRepository.delete(users);
            return ResponseEntity.ok().body(users);
        }
        return ResponseEntity.badRequest().body(users);
    }

    @Override
    @Transactional
    public ResponseEntity<Users> editUserEmailById(UserRequest userRequest, Integer userId) {
        Optional<Users> optionalUser = userRepository.findById(userId);
        Users user = userRepository.findById(userId).orElseGet(Users::new);
        if(optionalUser.isPresent()){
            user.setEmail(userRequest.getEmail());
            return ResponseEntity.ok().body(user);
        }
        return ResponseEntity.badRequest().body(user);
    }

    @Override
    public ResponseEntity<Users> addUser(UserRequest userRequest) {
        UserBuilder userBuilder = new UserBuilder();
        return ResponseEntity.ok().body(userRepository.save(userBuilder.build(userRequest)));
    }
}
