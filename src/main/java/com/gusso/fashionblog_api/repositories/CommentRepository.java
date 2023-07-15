package com.gusso.fashionblog_api.repositories;

import com.gusso.fashionblog_api.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comment, Long> {
}
