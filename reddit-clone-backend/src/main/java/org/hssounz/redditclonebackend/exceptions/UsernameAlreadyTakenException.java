package org.hssounz.redditclonebackend.exceptions;

public class UsernameAlreadyTakenException extends Throwable{
    public UsernameAlreadyTakenException(String username) {
        super(username + " is Already taken, Please choose another username");
    }
}
