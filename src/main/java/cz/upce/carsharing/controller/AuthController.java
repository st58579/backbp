package cz.upce.carsharing.controller;

import cz.upce.carsharing.dto.AuthenticationRequest;
import cz.upce.carsharing.dto.AuthenticationResponse;
import cz.upce.carsharing.dto.RegistrationUserRequest;
import cz.upce.carsharing.dto.UserDto;
import cz.upce.carsharing.exceptions.AuthCheckException;
import cz.upce.carsharing.model.User;
import cz.upce.carsharing.security.JwtTokenProvider;
import cz.upce.carsharing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.message.AuthException;

@RestController
@RequestMapping(value = "/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @PostMapping(value = "/login")
    public ResponseEntity<UserDto> login(@RequestBody AuthenticationRequest request) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            User user = (User) authenticate.getPrincipal();

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, jwtTokenProvider.generateAccessToken(user))
                    .body(user.toUserDto());
        }  catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping(value = "/registration")
    public UserDto register(@RequestBody RegistrationUserRequest request){
        return userService.register(request);
    }

//    @PostMapping(value = "/check")
//    public AuthenticationResponse check(String token){
//        if(jwtTokenProvider.validateToken(token)) return new AuthenticationResponse(jwtTokenProvider.getLogin(token), token);
//        else throw new AuthCheckException("invalid token");
//    }

}