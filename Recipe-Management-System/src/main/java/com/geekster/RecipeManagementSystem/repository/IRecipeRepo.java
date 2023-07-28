package com.geekster.RecipeManagementSystem.repository;

import com.geekster.RecipeManagementSystem.model.Recipe;
import com.geekster.RecipeManagementSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRecipeRepo extends JpaRepository<Recipe, Long> {

    Recipe findByRecipeName(String recipeName);


}
