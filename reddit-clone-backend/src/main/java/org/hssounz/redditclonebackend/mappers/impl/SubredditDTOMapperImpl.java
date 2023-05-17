package org.hssounz.redditclonebackend.mappers.impl;

import org.hssounz.redditclonebackend.dto.SubredditRequestDTO;
import org.hssounz.redditclonebackend.dto.SubredditResponseDTO;
import org.hssounz.redditclonebackend.mappers.SubredditDTOMapper;
import org.hssounz.redditclonebackend.model.Subreddit;
import org.springframework.stereotype.Service;

@Service
public class SubredditDTOMapperImpl implements SubredditDTOMapper {

    @Override
    public Subreddit fromSubredditRequest(SubredditRequestDTO subredditRequestDTO) {
        return Subreddit.builder()
                .name(subredditRequestDTO.getName())
                .description(subredditRequestDTO.getDescription())
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
