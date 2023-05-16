package org.hssounz.redditclonebackend.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity @Data @SuperBuilder
public class Comment {
    @Id
    private String id;
    @NotEmpty(message = "Comment must have a text")
    private String text;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    private Post post;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;




    @PrePersist
    private void creation(){
        this.id = UUID.randomUUID().toString();
    }
}
