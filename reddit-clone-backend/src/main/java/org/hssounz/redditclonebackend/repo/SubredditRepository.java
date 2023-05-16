package org.hssounz.redditclonebackend.repo;

import org.hssounz.redditclonebackend.model.Post;
import org.hssounz.redditclonebackend.model.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SubredditRepository extends JpaRepository<Subreddit, String> {
}
