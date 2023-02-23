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
    public ResponseEntity<Users> deleteUserByUsername(UserRequest userRequest) {

        Optional<Users> optionalUser =  userRepository.findByEmail(userRequest.getEmail());
        Users users = optionalUser.orElseGet(Users::new);
        if(optionalUser.isPresent()){
            userRepository.delete(users);
            return ResponseEntity.ok().body(users);
        }
        return ResponseEntity.badRequest().body(users);
    }

    @Override
    @Transactional
    public ResponseEntity<Users> editUserEmail(UserRequest userRequest) {
        Optional<Users> optionalUser = userRepository.findByEmail(userRequest.getEmail());
        Users users = optionalUser.orElseGet(Users::new);
        if(optionalUser.isPresent()){
            users.setEmail(userRequest.getEmail());
            return ResponseEntity.ok().body(users);
        }
        return ResponseEntity.badRequest().body(users);
    }

    @Override
    public ResponseEntity<Users> addUser(UserRequest userRequest) {
        UserBuilder userBuilder = new UserBuilder();
        return ResponseEntity.ok().body(userRepository.save(userBuilder.build(userRequest)));
    }
}
