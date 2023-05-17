package org.hssounz.redditclonebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class CommentResponseDTO {
    private String id;
    private String text;
    private String postId;
    private LocalDateTime createdAt;
    private String username;
}
