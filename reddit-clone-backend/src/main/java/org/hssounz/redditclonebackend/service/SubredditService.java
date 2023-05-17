package org.hssounz.redditclonebackend.service;

import org.hssounz.redditclonebackend.dto.SubredditRequestDTO;
import org.hssounz.redditclonebackend.dto.SubredditResponseDTO;
import org.hssounz.redditclonebackend.exceptions.SpringRedditException;

import java.util.List;

public interface SubredditService {
    SubredditResponseDTO save(SubredditRequestDTO subredditRequestDTO);
    List<SubredditResponseDTO> getAll();

    SubredditResponseDTO get(String id) throws SpringRedditException;
}
