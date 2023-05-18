package org.hssounz.redditclonebackend.service;

import org.hssounz.redditclonebackend.dto.AuthenticationResponseDTO;
import org.hssounz.redditclonebackend.dto.LoginRequest;
import org.hssounz.redditclonebackend.dto.RefreshTokenRequest;
import org.hssounz.redditclonebackend.dto.RegisterRequest;
import org.hssounz.redditclonebackend.exceptions.SpringRedditException;
import org.hssounz.redditclonebackend.model.Response;
import org.hssounz.redditclonebackend.model.User;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    public User signup(RegisterRequest registerRequest) throws SpringRedditException;
    public String verifyAccount(String token) throws SpringRedditException;
    public AuthenticationResponseDTO login(LoginRequest loginRequest);
    User getCurrentUser();

    AuthenticationResponseDTO refreshToken(RefreshTokenRequest refreshTokenRequest);
}
