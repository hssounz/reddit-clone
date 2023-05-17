package org.hssounz.redditclonebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class PostRequestDTO {
    private String postName;
    private String description;
    private String url;
    private String subredditName;
}
