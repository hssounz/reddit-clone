package org.hssounz.redditclonebackend.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@SuperBuilder
public class User {
    @Id
    private String userId;
    @Column(unique = true)
    @NotEmpty(message = "Username is required")
    private String username;
    @NotEmpty(message = "Password is required")
    private String password;
    @Email @NotEmpty(message = "Email is required") @Column(unique = true)
    private String email;
    @CreationTimestamp
    private Instant createdAt;
    private boolean enabled;

    @PrePersist
    private void creation(){
        this.userId = UUID.randomUUID().toString();
    }
}
