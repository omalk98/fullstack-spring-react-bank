package com.bank.backend.userAccount;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Controller for user account
 */
@RestController
@RequestMapping(path="api/users")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class UserAccountController {

    private final UserAccountService userService;

    @Autowired
    public UserAccountController(UserAccountService userService) {
        this.userService = userService;
    }

    @GetMapping("all")
    public ResponseEntity<List<UserAccount>> getUsers() {
        return ResponseEntity
                .ok()
                .body(userService.getUsers());
    }

    @GetMapping("user")
    public ResponseEntity<Optional<UserAccount>> getUser(@RequestParam("email") String email) {
        return ResponseEntity
                .ok()
                .body(userService.getUser(email));
    }

    @GetMapping("token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String refreshToken = request.getHeader(AUTHORIZATION);
        if(refreshToken != null) {
            try {
                Algorithm algorithm = Algorithm.HMAC256("SECRET".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                UserAccount user = userService.getUser(username)
                        .orElseThrow(() -> new UsernameNotFoundException(String.format("Username <<%s>> not found", username)));
                String accessToken = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .sign(algorithm);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", accessToken);
                tokens.put("refresh_token", refreshToken);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            }
            catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setHeader("Error", e.getMessage());
                Map<String, String> error = new HashMap<>();
                error.put("cod", Integer.toString(HttpServletResponse.SC_FORBIDDEN));
                error.put("msg", e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }
        else throw new IllegalStateException("Refresh token is missing");
    }

    @DeleteMapping( path = "remove/{studentID}")
    public ResponseEntity<String> deleteUser(@PathVariable("studentID") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.accepted().body("Removed userID: " + id);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> updateUser(
        @PathVariable("id") Long id,
        @RequestParam(required = false) String username,
        @RequestParam(required = false) String email) {

        userService.updateUser(id, username, email);
        return ResponseEntity.accepted().body("Updated userID: " + id);
    }
}
