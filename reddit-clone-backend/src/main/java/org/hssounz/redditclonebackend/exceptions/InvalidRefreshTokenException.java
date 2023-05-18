package org.hssounz.redditclonebackend.exceptions;

public class InvalidRefreshTokenException extends Exception{
    public InvalidRefreshTokenException(String token) {
        super(String.format("this token: %s is not valid", token));
    }
}
