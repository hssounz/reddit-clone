package org.hssounz.redditclonebackend.service;

import org.hssounz.redditclonebackend.dto.RegisterRequest;

public interface AuthService {
    public void signup(RegisterRequest registerRequest);
}
