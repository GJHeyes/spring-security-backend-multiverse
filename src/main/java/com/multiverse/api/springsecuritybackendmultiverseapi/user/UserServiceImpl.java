package com.multiverse.api.springsecuritybackendmultiverseapi.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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
    public ResponseEntity<User> deleteUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseGet(User::new);
        if(user.getId() == null){
            userRepository.delete(user);
            return ResponseEntity.ok().body(user);
        }
        return ResponseEntity.badRequest().body(user);
    }

    @Override
    @Transactional
    public ResponseEntity<User> editUserEmail(String email) {
        User user = userRepository.findByEmail(email).orElseGet(User::new);
        if(user.getEmail() == null){
            user.setEmail(email);
            return ResponseEntity.ok().body(user);
        }
        return ResponseEntity.badRequest().body(user);
    }

    @Override
    public ResponseEntity<User> addUser(UserRequest userRequest) {
        return ResponseEntity.ok().body(userRepository.save(userBuilder.build(userRequest)));
    }
}
