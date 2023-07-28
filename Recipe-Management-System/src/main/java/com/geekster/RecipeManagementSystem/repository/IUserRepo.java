package com.geekster.RecipeManagementSystem.repository;

import com.geekster.RecipeManagementSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User, Long> {
    User findFirstByUserEmail(String newEmail);
}
