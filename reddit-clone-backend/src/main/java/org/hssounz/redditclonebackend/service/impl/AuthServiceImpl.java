package org.hssounz.redditclonebackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.hssounz.redditclonebackend.dto.RegisterRequest;
import org.hssounz.redditclonebackend.model.User;
import org.hssounz.redditclonebackend.model.VerificationToken;
import org.hssounz.redditclonebackend.repo.UserRepository;
import org.hssounz.redditclonebackend.repo.VerificationTokenRepository;
import org.hssounz.redditclonebackend.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service @RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    @Override @Transactional
    public void signup(RegisterRequest registerRequest) {
        User user = User.builder()
                .username(registerRequest.getUsername())
                .email(passwordEncoder.encode(registerRequest.getEmail()))
                .password(registerRequest.getPassword())
                .enabled(false)
                .build();
        userRepository.save(user);

        String token = generateVerificationToken(user);
    }

    private String generateVerificationToken(User user){
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = VerificationToken.builder()
                .token(token)
                .user(user)
                .build();
       verificationTokenRepository.save(verificationToken);
        return token;
    }
}
