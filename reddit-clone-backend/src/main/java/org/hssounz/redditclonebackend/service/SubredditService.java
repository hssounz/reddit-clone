package org.hssounz.redditclonebackend.service;

import org.hssounz.redditclonebackend.dto.SubredditRequestDTO;
import org.hssounz.redditclonebackend.dto.SubredditResponseDTO;

import java.util.List;

public interface SubredditService {
    SubredditResponseDTO save(SubredditRequestDTO subredditRequestDTO);
    List<SubredditResponseDTO> getAll();
}
