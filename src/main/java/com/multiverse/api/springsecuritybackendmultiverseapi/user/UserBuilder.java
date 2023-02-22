package com.multiverse.api.springsecuritybackendmultiverseapi.user;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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