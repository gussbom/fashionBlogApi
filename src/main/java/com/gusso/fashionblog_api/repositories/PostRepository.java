package com.gusso.fashionblog_api.repositories;

import com.gusso.fashionblog_api.entities.Post;
import com.gusso.fashionblog_api.enums.DesignCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {

    Optional<Post> findPostByTitle(String title);
    List<Post> findPostByDesignCategory(DesignCategory designCategory);

}
