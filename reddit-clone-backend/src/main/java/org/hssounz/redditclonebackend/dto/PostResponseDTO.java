package org.hssounz.redditclonebackend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder @JsonInclude(NON_NULL)
public class PostResponseDTO {
    private String postId;
    private String postName;
    private String description;
    private String url;
    private String subredditName;
    private Integer voteCount;
    private List<CommentResponseDTO> comments;
    private String timeAgo;
}