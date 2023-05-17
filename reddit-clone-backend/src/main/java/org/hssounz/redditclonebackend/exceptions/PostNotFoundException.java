package org.hssounz.redditclonebackend.exceptions;

public class PostNotFoundException extends Throwable {
    public PostNotFoundException(String id) {
        super(String.format("Post: %s is not found.", id));
    }
}
