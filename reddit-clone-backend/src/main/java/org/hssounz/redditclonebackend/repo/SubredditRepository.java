package org.hssounz.redditclonebackend.repo;

import org.hssounz.redditclonebackend.model.Post;
import org.hssounz.redditclonebackend.model.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubredditRepository extends JpaRepository<Subreddit, String> {
    Optional<Subreddit> findByName(String name);
}
