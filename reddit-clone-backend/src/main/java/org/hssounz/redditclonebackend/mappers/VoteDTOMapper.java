package org.hssounz.redditclonebackend.mappers;

import org.hssounz.redditclonebackend.dto.VoteRequestDTO;
import org.hssounz.redditclonebackend.dto.VoteResponseDTO;
import org.hssounz.redditclonebackend.exceptions.SpringRedditException;
import org.hssounz.redditclonebackend.model.Vote;

public interface VoteDTOMapper {
    VoteResponseDTO fromVote(Vote vote);
    Vote fromVoteRequest(VoteRequestDTO voteRequestDTO) throws SpringRedditException;
}
