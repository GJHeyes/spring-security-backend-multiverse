package com.multiverse.api.springsecuritybackendmultiverseapi.user;

import lombok.experimental.SuperBuilder;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


@SuperBuilder
public class UserServiceImpl extends UserService{

    private final UserRepository userRepository;


    @Override
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userRepository.findAll());
    }

    @Override
    public ResponseEntity<User> deleteUserByUsername(String email) {

        Optional<User> optionalUser =  userRepository.findByEmail(email);
        User user = optionalUser.orElseGet(User::new);
        if(optionalUser.isPresent()){
            userRepository.delete(user);
            return ResponseEntity.ok().body(user);
        }
        return ResponseEntity.badRequest().body(user);
    }
}
