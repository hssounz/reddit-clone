package org.hssounz.redditclonebackend.service;

import org.hssounz.redditclonebackend.dto.RegisterRequest;
import org.hssounz.redditclonebackend.exceptions.SpringRedditException;

public interface AuthService {
    public void signup(RegisterRequest registerRequest) throws SpringRedditException;
    public String verifyAccount(String token) throws SpringRedditException;
}
