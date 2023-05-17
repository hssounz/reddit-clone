package org.hssounz.redditclonebackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.hssounz.redditclonebackend.dto.LoginRequest;
import org.hssounz.redditclonebackend.dto.RegisterRequest;
import org.hssounz.redditclonebackend.exceptions.InvalidVerificationTokenException;
import org.hssounz.redditclonebackend.exceptions.SpringRedditException;
import org.hssounz.redditclonebackend.exceptions.UserNotFoundException;
import org.hssounz.redditclonebackend.model.NotificationEmail;
import org.hssounz.redditclonebackend.model.Response;
import org.hssounz.redditclonebackend.model.User;
import org.hssounz.redditclonebackend.model.VerificationToken;
import org.hssounz.redditclonebackend.repo.UserRepository;
import org.hssounz.redditclonebackend.repo.VerificationTokenRepository;
import org.hssounz.redditclonebackend.security.JwtProvider;
import org.hssounz.redditclonebackend.service.AuthService;
import org.hssounz.redditclonebackend.service.MailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.UUID;

@Service @RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
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

    @Override
    public ResponseEntity<Response> login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        return ResponseEntity.ok(
                Response.builder()
                        .message("User Logged in successfully")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .data(
                                Map.of(
                                "username", ((org.springframework.security.core.userdetails.User)authentication.getPrincipal()).getUsername(),
                                "token", token
                                )
                        )
                        .build()
        );
    }

    @Override @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public User getCurrentUser() {
        return userRepository
                .findByUsername(
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getName()
                )
                .get();
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
