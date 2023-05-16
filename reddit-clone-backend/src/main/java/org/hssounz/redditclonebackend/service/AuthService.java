package org.hssounz.redditclonebackend.service;

import org.hssounz.redditclonebackend.dto.LoginRequest;
import org.hssounz.redditclonebackend.dto.RegisterRequest;
import org.hssounz.redditclonebackend.exceptions.SpringRedditException;
import org.hssounz.redditclonebackend.model.Response;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    public void signup(RegisterRequest registerRequest) throws SpringRedditException;
    public String verifyAccount(String token) throws SpringRedditException;
    public ResponseEntity<Response> login(LoginRequest loginRequest);
}
