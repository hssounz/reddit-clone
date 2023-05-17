package org.hssounz.redditclonebackend.mappers.impl;

import lombok.RequiredArgsConstructor;
import org.hssounz.redditclonebackend.dto.PostRequestDTO;
import org.hssounz.redditclonebackend.dto.PostResponseDTO;
import org.hssounz.redditclonebackend.exceptions.SpringRedditException;
import org.hssounz.redditclonebackend.exceptions.SubredditNotFoundException;
import org.hssounz.redditclonebackend.mappers.PostDTOMapper;
import org.hssounz.redditclonebackend.model.Post;
import org.hssounz.redditclonebackend.model.User;
import org.hssounz.redditclonebackend.repo.SubredditRepository;
import org.hssounz.redditclonebackend.service.AuthService;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class PostDTOMapperImpl implements PostDTOMapper {

    private final SubredditRepository subredditRepository;
    private final AuthService authService;

    @Override
    public Post fromPostRequest(PostRequestDTO postRequestDTO) throws SpringRedditException {
        Post post = Post.builder()
                .postName(postRequestDTO.getPostName())
                .description(postRequestDTO.getDescription())
                .subreddit(
                        subredditRepository
                                .findByName(postRequestDTO.getSubredditName())
                                .orElseThrow(
                                        () -> new SpringRedditException(
                                                new SubredditNotFoundException(postRequestDTO.getSubredditName())
                                        )
                                )
                )
                .user(
                        authService.getCurrentUser()
                )
                .build();
        return post;
    }

    @Override
    public PostResponseDTO fromPost(Post post) {
        return PostResponseDTO.builder()
                .postId(post.getPostId())
                .postName(post.getPostName())
                .description(post.getDescription())
                .subredditName(post.getSubreddit().getName())
                .url(post.getUrl())
                .build();
    }
}
