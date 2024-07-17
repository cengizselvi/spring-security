package edu.firat.finwise.security;

import edu.firat.finwise.business.dtos.user.UserDTO;
import edu.firat.finwise.entities.concretes.User;
import edu.firat.finwise.security.AuthenticationService;
import edu.firat.finwise.security.JwtService;
import edu.firat.finwise.security.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

  /*  @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody UserDTO userDTO) {
        User registeredUser = authenticationService.signup(userDTO);
        registeredUser.generateCustomerNumber();
        return ResponseEntity.ok(registeredUser);
    }*/

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto user) {
        User authenticatedUser = authenticationService.authenticate(user);
        CurrentUserDTO currentUser = new CurrentUserDTO(authenticatedUser.getId(), authenticatedUser.getEmail(), authenticatedUser.getCustomerNumber());

        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime(), currentUser);

        return ResponseEntity.ok(loginResponse);
    }
}