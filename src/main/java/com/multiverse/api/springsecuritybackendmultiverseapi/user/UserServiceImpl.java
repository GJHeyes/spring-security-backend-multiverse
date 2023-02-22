package com.multiverse.api.springsecuritybackendmultiverseapi.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    private final UserBuilder userBuilder;


    @Override
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userRepository.findAll());
    }

    @Override
    public ResponseEntity<User> deleteUserByUsername(UserRequest userRequest) {

        Optional<User> optionalUser =  userRepository.findByEmail(userRequest.getEmail());
        User user = optionalUser.orElseGet(User::new);
        if(optionalUser.isPresent()){
            userRepository.delete(user);
            return ResponseEntity.ok().body(user);
        }
        return ResponseEntity.badRequest().body(user);
    }

    @Override
    @Transactional
    public ResponseEntity<User> editUserEmail(UserRequest userRequest) {
        Optional<User> optionalUser = userRepository.findByEmail(userRequest.getEmail());
        User user = optionalUser.orElseGet(User::new);
        if(optionalUser.isPresent()){
            user.setEmail(userRequest.getEmail());
            return ResponseEntity.ok().body(user);
        }
        return ResponseEntity.badRequest().body(user);
    }

    @Override
    public ResponseEntity<User> addUser(UserRequest userRequest) {
        return ResponseEntity.ok().body(userRepository.save(userBuilder.build(userRequest)));
    }
}
