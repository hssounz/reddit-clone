package org.hssounz.redditclonebackend.service;

import lombok.RequiredArgsConstructor;
import org.hssounz.redditclonebackend.dto.CommentRequestDTO;
import org.hssounz.redditclonebackend.dto.CommentResponseDTO;
import org.hssounz.redditclonebackend.exceptions.SpringRedditException;
import org.hssounz.redditclonebackend.repo.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CommentService {
    CommentResponseDTO save(CommentRequestDTO commentRequestDTO) throws SpringRedditException;
    List<CommentResponseDTO> getPostComments(String postId);
    List<CommentResponseDTO> getUserComments(String username);
}
