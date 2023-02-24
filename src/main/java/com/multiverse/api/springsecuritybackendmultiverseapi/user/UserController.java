package com.multiverse.api.springsecuritybackendmultiverseapi.user;

import com.multiverse.api.springsecuritybackendmultiverseapi.user.impl.UserServiceImpl;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers(){
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Integer userId){
        return userService.deleteUserById(userId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> editUserEmail(@PathVariable("id") Integer userId, UserRequest userRequest){
        return userService.editUserEmail(userId, userRequest);
    }

    @PostMapping()
    public ResponseEntity<User> addUser(UserRequest userRequest){
        return userService.addUser(userRequest);
    }
}
