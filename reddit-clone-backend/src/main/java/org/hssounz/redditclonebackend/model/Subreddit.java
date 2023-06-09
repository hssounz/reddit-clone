package org.hssounz.redditclonebackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@SuperBuilder
public class Subreddit {
    @Id
    private String id;
    @NotEmpty(message = "Community name is required")
    @Column(unique = true)
    private String name;
    @NotEmpty(message = "Description is required")
    private String description;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Post> posts;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @PrePersist
    private void creation(){
        this.id = UUID.randomUUID().toString();
    }
}
