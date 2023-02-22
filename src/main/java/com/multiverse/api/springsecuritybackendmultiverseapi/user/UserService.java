package com.multiverse.api.springsecuritybackendmultiverseapi.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    public ResponseEntity<List<User>> getAllUsers();

    public ResponseEntity<User> deleteUserByUsername(String username);

    public ResponseEntity<User> editUserEmail(UserRequest userRequest);
}
