package org.hssounz.redditclonebackend.service;

import org.hssounz.redditclonebackend.model.RefreshToken;


public interface RefreshTokenService {
    RefreshToken generateRefreshToken();
}
