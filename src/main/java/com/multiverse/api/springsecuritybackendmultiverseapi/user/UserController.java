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
    public ResponseEntity<List<Users>> getAllUsers(){
        return userService.getAllUsers();
    }

    @DeleteMapping()
    public ResponseEntity<Users> deleteUser(@RequestBody @NonNull UserRequest userRequest){
        return userService.deleteUserByUsername(userRequest);
    }

    @PutMapping()
    public ResponseEntity<Users> editUserEmail(UserRequest userRequest){
        return userService.editUserEmail(userRequest);
    }

    @PostMapping()
    public ResponseEntity<Users> addUser(UserRequest userRequest){
        return userService.addUser(userRequest);
    }
}
