package org.hssounz.redditclonebackend.mappers.impl;

import lombok.RequiredArgsConstructor;
import org.hssounz.redditclonebackend.dto.CommentRequestDTO;
import org.hssounz.redditclonebackend.dto.CommentResponseDTO;
import org.hssounz.redditclonebackend.exceptions.PostNotFoundException;
import org.hssounz.redditclonebackend.exceptions.SpringRedditException;
import org.hssounz.redditclonebackend.mappers.CommentDTOMapper;
import org.hssounz.redditclonebackend.model.Comment;
import org.hssounz.redditclonebackend.repo.CommentRepository;
import org.hssounz.redditclonebackend.repo.PostRepository;
import org.hssounz.redditclonebackend.service.AuthService;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class CommentDTOMapperImpl implements CommentDTOMapper {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final AuthService authService;
    @Override
    public Comment fromCommentRequest(CommentRequestDTO commentRequestDTO) throws SpringRedditException {
        return Comment.builder()
                .text(commentRequestDTO.getText())
                .post(
                        postRepository.findById(
                                commentRequestDTO.getPostId()
                        ).orElseThrow(
                                () -> new SpringRedditException(
                                        new PostNotFoundException(commentRequestDTO.getPostId())
                                )
                        )
                )
                .user(
                        authService.getCurrentUser()
                )
                .build();
    }

    @Override
    public CommentResponseDTO fromComment(Comment comment) {
        return CommentResponseDTO.builder()
                .id(comment.getId())
                .createdAt(comment.getCreatedAt())
                .text(comment.getText())
                .postId(comment.getPost().getPostId())
                .username(comment.getUser().getUsername())
                .build();
    }
}
