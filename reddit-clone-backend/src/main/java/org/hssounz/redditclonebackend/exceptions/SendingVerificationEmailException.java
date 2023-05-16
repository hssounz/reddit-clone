package org.hssounz.redditclonebackend.exceptions;

public class SendingVerificationEmailException extends Exception{
    public SendingVerificationEmailException(String recipient) {
        super("Could not send email to: " + recipient);
    }
}
