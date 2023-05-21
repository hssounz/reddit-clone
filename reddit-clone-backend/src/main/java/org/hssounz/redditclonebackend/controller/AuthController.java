package org.hssounz.redditclonebackend.controller;

import lombok.RequiredArgsConstructor;
import org.hssounz.redditclonebackend.dto.LoginRequest;
import org.hssounz.redditclonebackend.dto.RefreshTokenRequest;
import org.hssounz.redditclonebackend.dto.RegisterRequest;
import org.hssounz.redditclonebackend.exceptions.SpringRedditException;
import org.hssounz.redditclonebackend.model.Response;
import org.hssounz.redditclonebackend.model.User;
import org.hssounz.redditclonebackend.service.AuthService;
import org.hssounz.redditclonebackend.service.RefreshTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    @PostMapping("/signup")
    public ResponseEntity<Response> signup(@RequestBody RegisterRequest registerRequest) {
        try {
            User user = authService.signup(registerRequest);
            return ResponseEntity.ok(
                    Response.builder()
                            .message("User Registration Successful, Please check your email for verification")
                            .status(HttpStatus.CREATED)
                            .statusCode(HttpStatus.CREATED.value())
                            .data(Map.of("email", registerRequest.getEmail(), "id", user.getUserId() ))
                            .build()
            );
        } catch (SpringRedditException e) {
            e.printStackTrace();
            return ResponseEntity.ok(
                    Response.builder()
                            .message(e.getMessage())
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .developerMessage(e.toString())
                            .build()
            );
        }

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
     try {
         return ResponseEntity.ok(
                 Response.builder()
                         .message("User Logged in successfully")
                         .status(HttpStatus.OK)
                         .statusCode(HttpStatus.OK.value())
                         .data(
                                 Map.of(
                                         "User",
                                         authService.login(loginRequest)
                                 )
                         )
                         .build()
         );
     } catch (AuthenticationException e){
         e.printStackTrace();
         return ResponseEntity.ok(
                 Response.builder()
                         .message(e.getMessage())
                         .status(HttpStatus.INTERNAL_SERVER_ERROR)
                         .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                         .developerMessage(e.toString())
                         .build()
         );
     }
    }
    @PostMapping("/refresh/token")
    public ResponseEntity<Response>refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest){
        try {
            return ResponseEntity.ok(
                    Response.builder()
                            .message("Token refreshed")
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .data(Map.of("User", authService.refreshToken(refreshTokenRequest)))
                            .build()
            );
        } catch (SpringRedditException e) {
            e.printStackTrace();
            return ResponseEntity.ok(
                    Response.builder()
                            .message(e.getMessage())
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .developerMessage(e.toString())
                            .build()
            );
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<Response> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest){
        try {
            return ResponseEntity.ok(
                    Response.builder()
                            .message(refreshTokenRequest.getUsername() + "Logged out successfully")
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .data(Map.of("id", refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken()), "deleted", true))
                            .build()
            );
        } catch (SpringRedditException e) {
            e.printStackTrace();
            return ResponseEntity.ok(
                    Response.builder()
                            .message(e.getMessage())
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .developerMessage(e.toString())
                            .build()
            );
        }
    }
}
