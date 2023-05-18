package org.hssounz.redditclonebackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity @Data @AllArgsConstructor @NoArgsConstructor @Builder
public class RefreshToken {
    @Id
    private String token;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @PrePersist
    void creation(){
        this.token = UUID.randomUUID().toString();
    }
}
