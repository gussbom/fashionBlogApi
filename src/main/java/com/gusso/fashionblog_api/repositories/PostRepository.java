package com.gusso.fashionblog_api.repositories;

import com.gusso.fashionblog_api.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Post, Long> {
}
