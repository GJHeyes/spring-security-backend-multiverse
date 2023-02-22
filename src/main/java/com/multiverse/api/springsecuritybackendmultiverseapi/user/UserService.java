package com.multiverse.api.springsecuritybackendmultiverseapi.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    public ResponseEntity<List<User>> getAllUsers();

    public ResponseEntity<User> deleteUserByEmail(String email);

    public ResponseEntity<User> editUserEmail(String email);

    public ResponseEntity<User> addUser(UserRequest userRequest);
}
