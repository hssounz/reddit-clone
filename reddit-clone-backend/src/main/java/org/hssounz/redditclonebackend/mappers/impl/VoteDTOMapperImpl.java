package org.hssounz.redditclonebackend.mappers.impl;

import lombok.RequiredArgsConstructor;
import org.hssounz.redditclonebackend.dto.VoteRequestDTO;
import org.hssounz.redditclonebackend.dto.VoteResponseDTO;
import org.hssounz.redditclonebackend.exceptions.PostNotFoundException;
import org.hssounz.redditclonebackend.exceptions.SpringRedditException;
import org.hssounz.redditclonebackend.mappers.VoteDTOMapper;
import org.hssounz.redditclonebackend.model.Vote;
import org.hssounz.redditclonebackend.repo.PostRepository;
import org.hssounz.redditclonebackend.repo.UserRepository;
import org.hssounz.redditclonebackend.service.AuthService;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class VoteDTOMapperImpl implements VoteDTOMapper {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    @Override
    public VoteResponseDTO fromVote(Vote vote) {
        return VoteResponseDTO.builder()
                .voteId(vote.getVoteId())
                .postName(vote.getPost().getPostName())
                .username(vote.getUser().getUsername())
                .voteType(vote.getVoteType())
                .build();
    }

    @Override
    public Vote fromVoteRequest(VoteRequestDTO voteRequestDTO) throws SpringRedditException {
        return Vote.builder()
                .voteType(voteRequestDTO.getVoteType())
                .post(
                        postRepository.findById(voteRequestDTO.getPostId()).orElseThrow(
                                () -> new SpringRedditException(
                                        new PostNotFoundException(voteRequestDTO.getPostId())
                                )
                        )
                )
                .user(authService.getCurrentUser())
                .build();
    }
}
