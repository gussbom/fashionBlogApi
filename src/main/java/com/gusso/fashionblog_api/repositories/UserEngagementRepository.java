package com.gusso.fashionblog_api.repositories;

import com.gusso.fashionblog_api.entities.User;
import com.gusso.fashionblog_api.entities.UserEngagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserEngagementRepository extends JpaRepository<UserEngagement, Long> {
    Optional<UserEngagement> findUserEngagementByUser(User user);
}
