package org.hssounz.redditclonebackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.hssounz.redditclonebackend.exceptions.InvalidRefreshTokenException;
import org.hssounz.redditclonebackend.exceptions.SpringRedditException;
import org.hssounz.redditclonebackend.model.RefreshToken;
import org.hssounz.redditclonebackend.repo.RefreshTokenRepository;
import org.hssounz.redditclonebackend.service.RefreshTokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    @Override @Transactional
    public RefreshToken generateRefreshToken() {
        RefreshToken refreshToken = RefreshToken.builder().build();
        return refreshTokenRepository.save(refreshToken);
    }

    @Override @Transactional(readOnly = true)
    public void validateRefreshToken(String token) throws SpringRedditException {
        refreshTokenRepository.findById(token).orElseThrow(
                () -> new SpringRedditException(
                        new InvalidRefreshTokenException(token)
                )
        );
    }

    @Override @Transactional
    public String deleteRefreshToken(String token) throws SpringRedditException {
        validateRefreshToken(token);
        refreshTokenRepository.deleteById(token);
        return token;
    }
}
