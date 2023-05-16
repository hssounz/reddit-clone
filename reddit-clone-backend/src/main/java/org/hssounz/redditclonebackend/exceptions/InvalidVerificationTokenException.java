package org.hssounz.redditclonebackend.exceptions;

public class InvalidVerificationTokenException extends Exception{
    public InvalidVerificationTokenException() {
        super("Invalid verification token");
    }
}
