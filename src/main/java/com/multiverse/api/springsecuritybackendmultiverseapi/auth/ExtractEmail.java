package com.multiverse.api.springsecuritybackendmultiverseapi.auth;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@NoArgsConstructor
@Component
public class ExtractEmail {

    @Autowired
    private JwtService jwtService;

    public String fromJwt(HttpServletRequest token){
        String authHeader = token.getHeader("Authorization");
        String jwtToken = authHeader.substring(7);
        return jwtService.extractUsername(jwtToken);
    }
}
