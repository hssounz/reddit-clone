package org.hssounz.redditclonebackend.repo;

import org.hssounz.redditclonebackend.model.Subreddit;
import org.hssounz.redditclonebackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
}
