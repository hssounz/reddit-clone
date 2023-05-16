package org.hssounz.redditclonebackend.repo;

import org.hssounz.redditclonebackend.model.Subreddit;
import org.hssounz.redditclonebackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {
}
