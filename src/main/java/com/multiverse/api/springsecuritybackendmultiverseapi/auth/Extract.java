package com.multiverse.api.springsecuritybackendmultiverseapi.auth;

import com.multiverse.api.springsecuritybackendmultiverseapi.user.User;
import com.multiverse.api.springsecuritybackendmultiverseapi.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Component
public class Extract {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    public String emailFromJwt(HttpServletRequest token){
        String authHeader = token.getHeader("Authorization");
        String jwtToken = authHeader.substring(7);
        return jwtService.extractUsername(jwtToken);
    }

    public ResponseEntity<List<User>> listOfUser(User user){
        String roleName = getRole(user);
        if(user.getId() != null){
            if(roleName.equals("GRUNT")){
                List<User> userList = new ArrayList<>();
                userList.add(userRepository.findById(user.getId()).orElseGet(User::new));
                return ResponseEntity.ok().body(userList);
            }
            return ResponseEntity.ok().body(userRepository.findAll());
        }
        return ResponseEntity.ok().body(new ArrayList<>());
    }

    public String getRole(User user){
        return user.getRole().getName();
    }
}
