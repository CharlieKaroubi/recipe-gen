package com.charlie.recipegen.repository;

import com.charlie.recipegen.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByDisplayName(String displayName);
}
