package com.multiverse.api.springsecuritybackendmultiverseapi.user;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers(){
        return userService.getAllUsers();
    }

    @DeleteMapping("/Delete")
    public ResponseEntity<User> deleteUser(@RequestBody @NonNull UserRequest userRequest){
        return userService.deleteUserByUsername(userRequest);
    }

    @PutMapping("/edit")
    public ResponseEntity<User> editUserEmail(UserRequest userRequest){
        return userService.editUserEmail(userRequest);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(UserRequest userRequest){
        return userService.addUser(userRequest);
    }
}
