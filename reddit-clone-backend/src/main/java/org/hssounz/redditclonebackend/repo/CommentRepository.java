package org.hssounz.redditclonebackend.repo;

import org.hssounz.redditclonebackend.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
    List<Comment> findByPostPostId(String postId);

    List<Comment> findByUserUsername(String username);
}
