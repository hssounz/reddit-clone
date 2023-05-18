package org.hssounz.redditclonebackend.service;

import org.hssounz.redditclonebackend.dto.VoteRequestDTO;
import org.hssounz.redditclonebackend.dto.VoteResponseDTO;
import org.hssounz.redditclonebackend.exceptions.SpringRedditException;

public interface VoteService {
    VoteResponseDTO vote(VoteRequestDTO voteRequestDTO) throws SpringRedditException;
}
