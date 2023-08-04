package com.workshop.bouali.controllers;

import com.workshop.bouali.User.UserP;
import com.workshop.bouali.User.UserRepository;
import com.workshop.bouali.User.UserService;
import com.workshop.bouali.config.JwtUtil;
import com.workshop.bouali.dao.UserDao;
import com.workshop.bouali.dto.AuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor // to have it  automatically in our constractor.
public class AuthenticationController {


    private final AuthenticationManager authenticationManager; //AuthenticationManager 3amlelha Bean f securityConfig
    private final UserDao userDao;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(
            @RequestBody AuthenticationRequest request) {

        //kol mnaaml login spring bch yet3ada bel process hedha
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        final UserDetails user = userDao.findUserByEmail(request.getEmail());
        if (user != null) {
            return ResponseEntity.ok(jwtUtil.generateToken(user));
        }
        return ResponseEntity.status(400).body("SOME ERROR ARE OCCURED");


    }


//
//eni w dali
//    @PostMapping("/authenticate")
//    public ResponseEntity<String> authenticate(
//            @RequestBody AuthenticationRequest request) {
//
//        //kol mnaaml login spring bch yet3ada bel process hedha
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
//        );
//        final Optional<UserP>  user = userRepository.findUserByEmail(request.getEmail());
//        if (user.isPresent()) {
//            return ResponseEntity.ok(jwtUtil.generateToken(user.get()));
//        }
//        return ResponseEntity.status(400).body("SOME ERROR ARE OCCURED");
//
//    }


//    @PostMapping("/authenticate")
//    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request) {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
//            );
//
//            Optional<UserP> userOptional = userService.getUserByEmail(request.getEmail());
//            if (userOptional.isPresent()) {
//                UserP user = userOptional.get();
//                return ResponseEntity.ok(jwtUtil.generateToken((UserDetails) user));
//            } else {
//                return ResponseEntity.status(400).body("User not found or some errors occurred.");
//            }
//        } catch (org.springframework.security.core.AuthenticationException e) {
//            return ResponseEntity.status(401).body("Invalid email or password.");
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body("Internal server error.");
//        }
//    }


}
