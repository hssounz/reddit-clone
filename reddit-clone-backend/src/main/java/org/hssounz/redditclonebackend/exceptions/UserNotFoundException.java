package org.hssounz.redditclonebackend.exceptions;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String username) {
        super("User not found: " + username);
    }
}
