package org.hssounz.redditclonebackend.repo;

import org.hssounz.redditclonebackend.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment, String> {
}
