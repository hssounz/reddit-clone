package org.hssounz.redditclonebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class AuthenticationResponseDTO {
    private String accessToken;
    private String refreshToken;
    private Instant expiresAt;
    private String userId;
    private String username;
    private String email;

}
