package org.hssounz.redditclonebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hssounz.redditclonebackend.model.VoteType;

@Data @AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoteResponseDTO {
    private String voteId;
    private VoteType voteType;
    private String postName;
    private String username;
}
