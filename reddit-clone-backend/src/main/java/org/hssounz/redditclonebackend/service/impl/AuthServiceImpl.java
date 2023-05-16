package org.hssounz.redditclonebackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.hssounz.redditclonebackend.dto.RegisterRequest;
import org.hssounz.redditclonebackend.exceptions.InvalidVerificationTokenException;
import org.hssounz.redditclonebackend.exceptions.SpringRedditException;
import org.hssounz.redditclonebackend.exceptions.UserNotFoundException;
import org.hssounz.redditclonebackend.model.NotificationEmail;
import org.hssounz.redditclonebackend.model.User;
import org.hssounz.redditclonebackend.model.VerificationToken;
import org.hssounz.redditclonebackend.repo.UserRepository;
import org.hssounz.redditclonebackend.repo.VerificationTokenRepository;
import org.hssounz.redditclonebackend.service.AuthService;
import org.hssounz.redditclonebackend.service.MailService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service @RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;
    @Override @Transactional
    public void signup(RegisterRequest registerRequest) throws SpringRedditException {
        User user = User.builder()
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .enabled(false)
                .build();
        userRepository.save(user);
        String token = generateVerificationToken(user);
        mailService.sendMail(
                NotificationEmail.builder()
                        .subject("Please Activate your reddit-clone account")
                        .recipient(user.getEmail())
                        .body("Thank you for signing up to Spring Reddit-Clone, \n" +
                                "Please click on the below url to activate your account: \n")
                        .activationUrl("http://localhost:8088/api/auth/accountVerification/" + token)
                        .build()
        );
    }

    @Override @Transactional
    public String verifyAccount(String token) throws SpringRedditException {
        String username = verificationTokenRepository.findByToken(token).orElseThrow(
                () -> new SpringRedditException(new InvalidVerificationTokenException())
        ).getUser().getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new SpringRedditException(new UserNotFoundException(username))
        );
        user.setEnabled(true);
        return user.getEmail();
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
