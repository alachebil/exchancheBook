package com.example.BookExchange.api;

import com.example.BookExchange.dto.UserDTO;
import com.example.BookExchange.entity.Role;
import com.example.BookExchange.entity.UserP;
import com.example.BookExchange.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.List;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;


import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserResource {

    private final UserService userService;


    //chnya ResponseENTITY???
//    @GetMapping("/users")
//    public ResponseEntity<List<UserP>> getUsers() {
//
//        return ResponseEntity.ok().body(userService.getUsers());
//    }


    @PostMapping("/user/save")
    public ResponseEntity<UserP> saveUser(@RequestBody UserP userP) {
        // chnya el uri eli zedha
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(userP));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }


    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }


    @DeleteMapping(path = "/user/del/{userId}")
    public void deleteStudent(@PathVariable("userId") Long userId ) throws IllegalAccessException {
        userService.deleteUser(userId);
    }

    @PutMapping(path = "/user/update/{userId}")
    public void updateStudent(@PathVariable("userId") Long userId,@RequestParam(required = false) String name,@RequestParam(required = false) String username,@RequestParam(required = false) String password ) throws IllegalAccessException {
        userService.updateUser(userId,name,username,password);
    }

    ////////////////////////////////////////////////////////////


    @GetMapping("/users")

    public List<UserDTO> getUsers() {
        return userService.getUsers();

    }



    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                UserP userP = userService.getUser(username);
                String access_token = JWT.create()
                        .withSubject(userP.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURI().toString())
                        .withClaim("roles", userP.getRoles().stream().map(Role::getName).collect(toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());

                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }

        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }

}


@Data
class RoleToUserForm {
    private String username;
    private String roleName;
}