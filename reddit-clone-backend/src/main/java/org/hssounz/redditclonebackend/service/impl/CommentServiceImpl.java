package org.hssounz.redditclonebackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.hssounz.redditclonebackend.dto.CommentRequestDTO;
import org.hssounz.redditclonebackend.dto.CommentResponseDTO;
import org.hssounz.redditclonebackend.exceptions.SpringRedditException;
import org.hssounz.redditclonebackend.mappers.CommentDTOMapper;
import org.hssounz.redditclonebackend.model.Comment;
import org.hssounz.redditclonebackend.model.NotificationEmail;
import org.hssounz.redditclonebackend.repo.CommentRepository;
import org.hssounz.redditclonebackend.service.CommentService;
import org.hssounz.redditclonebackend.service.MailContentBuilder;
import org.hssounz.redditclonebackend.service.MailService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentDTOMapper commentDTOMapper;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;
    @Override
    public CommentResponseDTO save(CommentRequestDTO commentRequestDTO) throws SpringRedditException {
        Comment comment = commentRepository
                .save(
                        commentDTOMapper.fromCommentRequest(commentRequestDTO)
                );
        String message = mailContentBuilder.build(
                comment.getPost().getUser().getUsername() + "posted a comment on your post.",
                "http://localhost:8088/api/posts/" + comment.getPost().getPostId()
        );
        mailService.sendMail(
                NotificationEmail.builder()
                        .subject(comment.getUser().getUsername() + "Commented on your post.")
                        .recipient(comment.getPost().getUser().getEmail())
                        .body(message)
                        .activationUrl("http://localhost:8088/api/posts/" + comment.getPost().getPostId())
                        .build()
        );
        return commentDTOMapper.fromComment(comment);
    }
    @Override
    public List<CommentResponseDTO> getPostComments(String postId) {
        return commentRepository.findByPostPostId(postId).stream().map(
                commentDTOMapper::fromComment
        ).collect(Collectors.toList());
    }

    @Override
    public List<CommentResponseDTO> getUserComments(String username) {
        return commentRepository.findByUserUsername(username).stream().map(
                commentDTOMapper::fromComment
        ).collect(Collectors.toList());
    }
}
