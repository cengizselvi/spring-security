package edu.firat.finwise.security;


import edu.firat.finwise.business.dtos.user.UserDTO;
import edu.firat.finwise.data.UserDao;
import edu.firat.finwise.entities.concretes.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserDao userDao;


    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserDao userDao,
            AuthenticationManager authenticationManager
    ) {
        this.authenticationManager = authenticationManager;
        this.userDao = userDao;
    }

    public User authenticate(LoginUserDto user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );

        return userDao.findByIdentityNumber(user.getUsername())
                .orElseThrow();
    }
}