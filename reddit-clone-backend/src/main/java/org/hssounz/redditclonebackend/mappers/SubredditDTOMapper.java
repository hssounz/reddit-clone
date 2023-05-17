package org.hssounz.redditclonebackend.mappers;

import org.hssounz.redditclonebackend.dto.SubredditRequestDTO;
import org.hssounz.redditclonebackend.dto.SubredditResponseDTO;
import org.hssounz.redditclonebackend.model.Subreddit;

public interface SubredditDTOMapper {
    Subreddit fromSubredditRequest(SubredditRequestDTO subredditRequestDTO);
    SubredditResponseDTO fromSubreddit(Subreddit subreddit);
}
