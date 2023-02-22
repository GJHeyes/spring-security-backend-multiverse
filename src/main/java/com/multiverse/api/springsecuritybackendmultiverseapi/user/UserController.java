package com.multiverse.api.springsecuritybackendmultiverseapi.user;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers(){
        return userService.getAllUsers();
    }

    @DeleteMapping("/Delete")
    public ResponseEntity<User> deleteUser(@RequestBody @NonNull String email){
        return userService.deleteUserByUsername(email);
    }

    @PutMapping("/edit")
    public ResponseEntity<User> editUser(UserRequest userRequest){
        return userService.editUserEmail(userRequest);
    }
}
