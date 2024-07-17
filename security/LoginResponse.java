package edu.firat.finwise.security;


import edu.firat.finwise.business.dtos.user.UserDTO;
import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private long expiresIn;
    private CurrentUserDTO user;

    // Constructor
    public LoginResponse(String token, long expiresIn, CurrentUserDTO user) {
        this.token = token;
        this.expiresIn = expiresIn;
        this.user = user;
    }


}