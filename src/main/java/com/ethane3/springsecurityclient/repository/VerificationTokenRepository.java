package com.ethane3.springsecurityclient.repository;

import com.ethane3.springsecurityclient.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends
        JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);
}
