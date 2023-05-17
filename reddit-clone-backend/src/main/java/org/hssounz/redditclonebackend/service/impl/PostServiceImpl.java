package org.hssounz.redditclonebackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.hssounz.redditclonebackend.dto.PostRequestDTO;
import org.hssounz.redditclonebackend.dto.PostResponseDTO;
import org.hssounz.redditclonebackend.exceptions.PostNotFoundException;
import org.hssounz.redditclonebackend.exceptions.SpringRedditException;
import org.hssounz.redditclonebackend.mappers.PostDTOMapper;
import org.hssounz.redditclonebackend.repo.PostRepository;
import org.hssounz.redditclonebackend.service.PostService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostDTOMapper postDTOMapper;
    private final PostRepository postRepository;
    @Override @Transactional
    public PostResponseDTO save(PostRequestDTO postRequestDTO) throws SpringRedditException {
        return postDTOMapper.fromPost(
                postRepository.save(
                        postDTOMapper.fromPostRequest(postRequestDTO)
                )
        );
    }

    @Override @Transactional(readOnly = true)
    public PostResponseDTO getPost(String id) throws SpringRedditException {
        return postDTOMapper.fromPost(
                postRepository.findById(id).orElseThrow(
                        () -> new SpringRedditException(
                                new PostNotFoundException(id)
                        )
                )
        );
    }

    @Override @Transactional(readOnly = true)
    public List<PostResponseDTO> getAllPosts() {
        return postRepository.findAll().stream().map(
                postDTOMapper::fromPost
        ).collect(Collectors.toList());
    }
}
