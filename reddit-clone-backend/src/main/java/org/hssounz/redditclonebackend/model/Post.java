package org.hssounz.redditclonebackend.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.UUID;

@Entity @Data @SuperBuilder
public class Post {
    @Id
    private String postId;
    @Column @NotEmpty(message = "Post Name cannot be empty or Null")
    private String postName;
    @Nullable
    private String url;
    @Nullable @Lob
    private String description;
    @CreationTimestamp
    private Integer voteCount;
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
    private Instant createdAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Subreddit subreddit;

    @PrePersist
    private void creation(){
        this.postId = UUID.randomUUID().toString();
    }
}
