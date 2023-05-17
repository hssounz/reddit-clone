package org.hssounz.redditclonebackend.repo;

import org.hssounz.redditclonebackend.model.Comment;
import org.hssounz.redditclonebackend.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {
}
