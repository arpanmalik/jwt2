package com.example.demo2PractiseSecurity.controller;

import com.example.demo2PractiseSecurity.models.User;
import com.example.demo2PractiseSecurity.service.JwtService;
import com.example.demo2PractiseSecurity.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("home")
    public String home(){
        return "Hello! Welcome to home.";
    }

    @GetMapping("admin")
    public String admin(){
        return "Hello Admin";
    }

    @GetMapping("user")
    public Object user(Authentication authentication){
        return ResponseEntity.status(HttpStatus.OK).body("Hello User"+ authentication.getPrincipal().toString());
    }

    @PostMapping("/register")
    public Object addUser(@RequestBody User user){
        if(user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Username");
        }
        User savedUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PostMapping("/login")
    public Object login(@RequestBody User user){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        if(authentication.isAuthenticated()){
            String token = jwtService.generateToken(user.getUsername());
            return ResponseEntity.status(HttpStatus.OK).body(token);
        }
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Credentials");
    }
}
