package org.hssounz.redditclonebackend.repo;

import org.hssounz.redditclonebackend.model.User;
import org.hssounz.redditclonebackend.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VoteRepository extends JpaRepository<Vote, String> {
}
