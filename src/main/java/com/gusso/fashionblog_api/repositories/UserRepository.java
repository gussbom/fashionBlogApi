package com.gusso.fashionblog_api.repositories;

import com.gusso.fashionblog_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<User, Long> {

}
