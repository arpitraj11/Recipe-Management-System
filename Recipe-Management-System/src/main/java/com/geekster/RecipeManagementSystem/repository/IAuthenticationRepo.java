package com.geekster.RecipeManagementSystem.repository;

import com.geekster.RecipeManagementSystem.model.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthenticationRepo extends JpaRepository<AuthenticationToken, Long> {

    AuthenticationToken findFirstByTokenValue(String userToken);
}
