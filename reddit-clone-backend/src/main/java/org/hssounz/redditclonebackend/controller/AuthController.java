package org.hssounz.redditclonebackend.controller;

import lombok.RequiredArgsConstructor;
import org.hssounz.redditclonebackend.dto.LoginRequest;
import org.hssounz.redditclonebackend.dto.RegisterRequest;
import org.hssounz.redditclonebackend.exceptions.SpringRedditException;
import org.hssounz.redditclonebackend.model.Response;
import org.hssounz.redditclonebackend.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<Response> signup(@RequestBody RegisterRequest registerRequest) throws SpringRedditException {
        authService.signup(registerRequest);
        return ResponseEntity.ok(
                Response.builder()
                        .message("User Registration Successful, Please check your email for verification")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .data(Map.of("user email: ", registerRequest.getEmail()))
                        .build()
        );
    }

    @GetMapping("/accountVerification/{token}")
    public ResponseEntity<Response> verifyAccount(@PathVariable String token){
        try {
            String username = authService.verifyAccount(token);
            return ResponseEntity.ok(
                    Response.builder()
                            .message("User verified successfully: " + username)
                            .statusCode(HttpStatus.OK.value())
                            .status(HttpStatus.OK)
                            .build()
            );
        } catch (SpringRedditException e) {
            e.printStackTrace();
            return ResponseEntity.ok(
                    Response.builder()
                            .message(e.getMessage())
                            .statusCode(e.hashCode())
                            .build()
            );
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }
}
