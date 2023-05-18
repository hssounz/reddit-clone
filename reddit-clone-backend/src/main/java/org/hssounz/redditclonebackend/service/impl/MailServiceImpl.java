package org.hssounz.redditclonebackend.service.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.hssounz.redditclonebackend.exceptions.SendingVerificationEmailException;
import org.hssounz.redditclonebackend.exceptions.SpringRedditException;
import org.hssounz.redditclonebackend.model.NotificationEmail;
import org.hssounz.redditclonebackend.service.MailContentBuilder;
import org.hssounz.redditclonebackend.service.MailService;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service @Data @RequiredArgsConstructor @Slf4j
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;
    private final MailContentBuilder mailContentBuilder;
    @Override @Async
    public void sendMail(NotificationEmail notificationEmail) throws SpringRedditException {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("hassenbensaad55@gmail.com");
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(mailContentBuilder.build(notificationEmail.getBody(), notificationEmail.getActivationUrl()));
        };
        try {
            mailSender.send(messagePreparator);
            log.info("Activation mail sent to + " + notificationEmail.getRecipient());
        } catch (MailException e) {
            e.printStackTrace();
            throw new SpringRedditException(
                    new SendingVerificationEmailException(
                            notificationEmail.getRecipient()
                    )
            );
        }
    }
}
