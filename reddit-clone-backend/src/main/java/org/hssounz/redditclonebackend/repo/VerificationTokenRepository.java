package org.hssounz.redditclonebackend.repo;

import org.hssounz.redditclonebackend.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
}
