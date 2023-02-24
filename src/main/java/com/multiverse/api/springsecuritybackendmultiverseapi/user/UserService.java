package com.multiverse.api.springsecuritybackendmultiverseapi.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


public interface UserService extends UserDetailsService {

    public ResponseEntity<List<User>> getAllUsers();

    public ResponseEntity<User> deleteUserById(Integer userId);

    public ResponseEntity<User> editUserEmail(Integer userId, UserRequest userRequest);

    public ResponseEntity<User> addUser(UserRequest userRequest);

    @Override
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
}
