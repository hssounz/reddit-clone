package org.hssounz.redditclonebackend.exceptions;

import org.hssounz.redditclonebackend.model.VoteType;

public class AlreadyVotedToPostException extends Exception{
    public AlreadyVotedToPostException(VoteType voteType, String postId) {
        super(String.format("You already voted %s on %s", voteType, postId));
    }
}
