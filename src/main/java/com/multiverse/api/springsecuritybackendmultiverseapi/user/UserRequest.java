package com.multiverse.api.springsecuritybackendmultiverseapi.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private Integer id;
    private String email;
    private String firstname;
    private String lastname;
    private String password;
    private Role role;

}
