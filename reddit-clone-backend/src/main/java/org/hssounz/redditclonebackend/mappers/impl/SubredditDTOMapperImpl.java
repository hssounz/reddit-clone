package org.hssounz.redditclonebackend.mappers.impl;

import lombok.RequiredArgsConstructor;
import org.hssounz.redditclonebackend.dto.SubredditRequestDTO;
import org.hssounz.redditclonebackend.dto.SubredditResponseDTO;
import org.hssounz.redditclonebackend.mappers.SubredditDTOMapper;
import org.hssounz.redditclonebackend.model.Subreddit;
import org.hssounz.redditclonebackend.service.AuthService;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class SubredditDTOMapperImpl implements SubredditDTOMapper {
    private final AuthService authService;

    @Override
    public Subreddit fromSubredditRequest(SubredditRequestDTO subredditRequestDTO) {
        return Subreddit.builder()
                .name(subredditRequestDTO.getName())
                .description(subredditRequestDTO.getDescription())
                .user(authService.getCurrentUser())
                .build();
    }

    @Override
    public SubredditResponseDTO fromSubreddit(Subreddit subreddit) {
        return SubredditResponseDTO.builder()
                .id(subreddit.getId())
                .name(subreddit.getName())
                .description(subreddit.getDescription())
                .numberOfPost(subreddit.getPosts() != null ? subreddit.getPosts().size() : 0)
                .build();
    }
}
