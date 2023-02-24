package com.multiverse.api.springsecuritybackendmultiverseapi.user.impl;

import com.multiverse.api.springsecuritybackendmultiverseapi.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userRepository.findAll());
    }

    @Override
    public ResponseEntity<User> deleteUserById(Integer userId) {

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
    public ResponseEntity<User> editUserEmail(Integer userId, UserRequest userRequest) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.orElseGet(User::new);
        if(optionalUser.isPresent()){
            user.setEmail(userRequest.getEmail());
            return ResponseEntity.ok().body(user);
        }
        return ResponseEntity.badRequest().body(user);
    }

    @Override
    public ResponseEntity<User> addUser(UserRequest userRequest) {
        UserBuilder userBuilder = new UserBuilder();
        return ResponseEntity.ok().body(userRepository.save(userBuilder.build(userRequest)));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseGet(User::new);
    }
}
