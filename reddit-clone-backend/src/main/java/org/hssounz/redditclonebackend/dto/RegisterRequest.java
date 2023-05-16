package org.hssounz.redditclonebackend.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data @SuperBuilder
public class RegisterRequest {
    private String email;
    private String username;
    private String password;
}
