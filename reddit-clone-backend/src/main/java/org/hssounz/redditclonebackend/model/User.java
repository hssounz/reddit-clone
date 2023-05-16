package org.hssounz.redditclonebackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@SuperBuilder
public class User {
    @Id
    private String userId;
    @Column(unique = true)
    @NotEmpty(message = "Username is required")
    private String username;
    @NotEmpty(message = "Password is required")
    private String password;
    @Email(message = "Please provide a valid address email")
    @NotEmpty(message = "Email is required")
    @Column(unique = true)
    private String email;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private boolean enabled;

    @PrePersist
    private void creation(){
        this.userId = UUID.randomUUID().toString();
    }
}
