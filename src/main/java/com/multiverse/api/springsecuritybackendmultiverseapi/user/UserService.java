package com.multiverse.api.springsecuritybackendmultiverseapi.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserServiceImpl userServiceImpl;

    public ResponseEntity<List<User>> getAllUsers(){
        return userServiceImpl.getAllUsers();
    }

    public ResponseEntity<User> deleteUserByUsername(String username){
        return userServiceImpl.deleteUserByUsername(username);
    }
}
