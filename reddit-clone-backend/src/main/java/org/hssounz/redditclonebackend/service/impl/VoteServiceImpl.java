package org.hssounz.redditclonebackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.hssounz.redditclonebackend.dto.VoteRequestDTO;
import org.hssounz.redditclonebackend.dto.VoteResponseDTO;
import org.hssounz.redditclonebackend.exceptions.AlreadyVotedToPostException;
import org.hssounz.redditclonebackend.exceptions.SpringRedditException;
import org.hssounz.redditclonebackend.mappers.VoteDTOMapper;
import org.hssounz.redditclonebackend.model.Vote;
import org.hssounz.redditclonebackend.model.VoteType;
import org.hssounz.redditclonebackend.repo.PostRepository;
import org.hssounz.redditclonebackend.repo.VoteRepository;
import org.hssounz.redditclonebackend.service.VoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service @RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {
    private final VoteRepository voteRepository;
    private final VoteDTOMapper voteDTOMapper;
    private final PostRepository postRepository;
    @Override @Transactional
    public VoteResponseDTO vote(VoteRequestDTO voteRequestDTO) throws SpringRedditException {
        Vote vote = voteDTOMapper.fromVoteRequest(voteRequestDTO);
        Optional<Vote> oldVote = voteRepository
                .findTopByPostPostIdAndUserUsernameOrderByVoteIdDesc(
                        vote.getPost().getPostId(),
                        vote.getUser().getUsername()
                );
        if (oldVote.isPresent() && oldVote.get().getVoteType().equals(vote.getVoteType())) {
            throw new SpringRedditException(
                    new AlreadyVotedToPostException(vote.getVoteType(), vote.getPost().getPostId())
            );
        }
//        else if (oldVote.isPresent() && !oldVote.get().getVoteType().equals(vote.getVoteType())){
//            voteRepository.deleteById(oldVote.get().getVoteId());
//        }
        vote.getPost().setVoteCount(
                vote.getPost().getVoteCount() != null
                        ? vote.getPost().getVoteCount() + (vote.getVoteType().equals(VoteType.UP_VOTE) ? 1 : -1)
                        : (vote.getVoteType().equals(VoteType.UP_VOTE) ? 1 : -1)
        );
        postRepository.save(vote.getPost());
        return voteDTOMapper.fromVote(
                voteRepository.save(
                        vote
                )
        );
    }
}
