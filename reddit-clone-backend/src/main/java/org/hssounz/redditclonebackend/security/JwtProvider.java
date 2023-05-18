package org.hssounz.redditclonebackend.security;

import com.nimbusds.jose.util.JSONObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;

@Service @RequiredArgsConstructor
public class JwtProvider {
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
    @Value("${jwt.expiration.time}")
    private Long jwtExpirationInMillis;

    public String generateToken(Authentication authentication){
        User principal = (User) authentication.getPrincipal();
        return generateTokenWithUsername(principal.getUsername(), jwtExpirationInMillis);
    }

    public String generateTokenWithUsername(String username, Long expiration){
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusMillis(expiration))
                .subject(username)
                .claim("scope", "ROLE_USER")
                .build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public Long getJwtExpirationInMillis(){
        return jwtExpirationInMillis;
    }


    public boolean validateToken(String jwt) throws Exception {
        try {
            Jwt decode = this.jwtDecoder.decode(jwt);
            return true;
        } catch (Exception e) {
            throw new Exception(String.format("token %s is not valid", jwt));
        }
    }

    public String getUsernameFromJwt(String jwt) {
        return this.jwtDecoder.decode(jwt).getSubject();
    }
}
