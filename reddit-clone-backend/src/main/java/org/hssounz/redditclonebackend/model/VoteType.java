package org.hssounz.redditclonebackend.model;

public enum VoteType {
    UP_VOTE(1), DOWN_VOTE(-1),
    ;

    VoteType(int direction) {}
}
