package com.multiverse.api.springsecuritybackendmultiverseapi.user;


import org.springframework.stereotype.Component;

@Component
public class UserBuilder {

    public User build(UserRequest userRequest){
        return User.builder()
                .email(userRequest.getEmail())
                .firstname(userRequest.getFirstname())
                .lastname(userRequest.getLastname())
                .password(userRequest.getPassword())
                .build();
    }
}
