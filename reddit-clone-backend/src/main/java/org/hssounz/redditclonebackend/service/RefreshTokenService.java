package org.hssounz.redditclonebackend.service;

import org.hssounz.redditclonebackend.exceptions.SpringRedditException;
import org.hssounz.redditclonebackend.model.RefreshToken;


public interface RefreshTokenService {
    RefreshToken generateRefreshToken();
    void validateRefreshToken(String token) throws SpringRedditException;
    String deleteRefreshToken(String token) throws SpringRedditException;
}
