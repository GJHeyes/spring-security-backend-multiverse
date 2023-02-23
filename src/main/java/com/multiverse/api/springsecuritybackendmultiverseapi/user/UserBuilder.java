package com.multiverse.api.springsecuritybackendmultiverseapi.user;


public class UserBuilder {

    public Users build(UserRequest userRequest){
        return Users.builder()
                .email(userRequest.getEmail())
                .firstname(userRequest.getFirstname())
                .lastname(userRequest.getLastname())
                .password(userRequest.getPassword())
                .build();
    }
}
