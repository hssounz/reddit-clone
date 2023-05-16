package org.hssounz.redditclonebackend.service;

import org.hssounz.redditclonebackend.exceptions.SpringRedditException;
import org.hssounz.redditclonebackend.model.NotificationEmail;

public interface MailService {
    void sendMail(NotificationEmail notificationEmail) throws SpringRedditException;
}
