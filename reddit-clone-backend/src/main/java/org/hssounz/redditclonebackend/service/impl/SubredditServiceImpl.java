package org.hssounz.redditclonebackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.hssounz.redditclonebackend.dto.SubredditRequestDTO;
import org.hssounz.redditclonebackend.dto.SubredditResponseDTO;
import org.hssounz.redditclonebackend.mappers.SubredditDTOMapper;
import org.hssounz.redditclonebackend.repo.SubredditRepository;
import org.hssounz.redditclonebackend.service.SubredditService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class SubredditServiceImpl implements SubredditService {

    private final SubredditRepository subredditRepository;
    private final SubredditDTOMapper subredditDTOMapper;
    @Override @Transactional
    public SubredditResponseDTO save(SubredditRequestDTO subredditRequestDTO) {
        return subredditDTOMapper.fromSubreddit(
                subredditRepository.save(
                        subredditDTOMapper.fromSubredditRequest(subredditRequestDTO)
                )
        );
    }

    @Override @Transactional(readOnly = true)
    public List<SubredditResponseDTO> getAll() {
        return subredditRepository.findAll().stream().map(
                subredditDTOMapper::fromSubreddit
        ).collect(Collectors.toList());
    }
}
