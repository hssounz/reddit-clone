package org.hssounz.redditclonebackend.mappers.impl;

import lombok.RequiredArgsConstructor;
import org.hssounz.redditclonebackend.dto.PostRequestDTO;
import org.hssounz.redditclonebackend.dto.PostResponseDTO;
import org.hssounz.redditclonebackend.exceptions.SpringRedditException;
import org.hssounz.redditclonebackend.exceptions.SubredditNotFoundException;
import org.hssounz.redditclonebackend.mappers.PostDTOMapper;
import org.hssounz.redditclonebackend.model.Post;
import org.hssounz.redditclonebackend.repo.SubredditRepository;
import org.hssounz.redditclonebackend.service.AuthService;
import org.hssounz.redditclonebackend.service.CommentService;
import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

@Service @RequiredArgsConstructor
public class PostDTOMapperImpl implements PostDTOMapper {

    private final SubredditRepository subredditRepository;
    private final CommentService commentService;
    private final AuthService authService;

    @Override
    public Post fromPostRequest(PostRequestDTO postRequestDTO) throws SpringRedditException {
        return Post.builder()
                .postName(postRequestDTO.getPostName())
                .description(postRequestDTO.getDescription())
                .url(postRequestDTO.getUrl())
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
    }

    @Override
    public PostResponseDTO fromPost(Post post) {
        PrettyTime prettyTime = new PrettyTime();
        return PostResponseDTO.builder()
                .postId(post.getPostId())
                .postName(post.getPostName())
                .description(post.getDescription())
                .subredditName(post.getSubreddit().getName())
                .url(post.getUrl())
                .comments(commentService.getPostComments(post.getPostId()))
                .voteCount(post.getVoteCount())
                .timeAgo(
                        prettyTime.format(
                                Date.from(post.getCreatedAt().toInstant(OffsetDateTime.now().getOffset()))
                        )
                )
                .build();
    }
}
