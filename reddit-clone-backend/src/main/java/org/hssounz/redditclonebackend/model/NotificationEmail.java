package org.hssounz.redditclonebackend.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data @SuperBuilder
public class NotificationEmail {
    private String subject;
    private String recipient;
    private String body;
    private String activationUrl;
}
