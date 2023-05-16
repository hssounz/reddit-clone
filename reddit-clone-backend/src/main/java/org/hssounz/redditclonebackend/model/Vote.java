package org.hssounz.redditclonebackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@SuperBuilder
public class Vote {
    @Id
    private String voteId;
    @Enumerated(EnumType.STRING)
    private VoteType voteType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    private Post post;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    @PrePersist
    private void creation(){
        this.voteId = UUID.randomUUID().toString();
    }
}
