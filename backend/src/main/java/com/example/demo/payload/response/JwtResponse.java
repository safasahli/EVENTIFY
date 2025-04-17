package com.example.demo.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JwtResponse {

    private String accessToken;
    private String tokenType = "Bearer";
    private String username;
    private String email;
    private List<String> roles;  // List of roles
    private Long id;  // Add the id field

    public JwtResponse(String accessToken, String username, String email, List<String> roles, Long id) {
        this.accessToken = accessToken;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.id = id;  // Set the id
    }
}
