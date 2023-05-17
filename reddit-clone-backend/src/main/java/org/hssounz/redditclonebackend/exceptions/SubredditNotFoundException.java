package org.hssounz.redditclonebackend.exceptions;

public class SubredditNotFoundException extends Throwable {
    public SubredditNotFoundException(String id) {
        super(String.format("Subreddit: %s Not found", id));
    }
}
