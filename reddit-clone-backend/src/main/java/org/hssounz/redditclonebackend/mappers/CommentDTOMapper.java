package org.hssounz.redditclonebackend.mappers;

import org.hssounz.redditclonebackend.dto.CommentRequestDTO;
import org.hssounz.redditclonebackend.dto.CommentResponseDTO;
import org.hssounz.redditclonebackend.exceptions.SpringRedditException;
import org.hssounz.redditclonebackend.model.Comment;

public interface CommentDTOMapper {
    Comment fromCommentRequest(CommentRequestDTO commentRequestDTO) throws SpringRedditException;
    CommentResponseDTO fromComment(Comment comment);
}
