package com.geekster.RecipeManagementSystem.service;

import com.geekster.RecipeManagementSystem.model.Recipe;
import com.geekster.RecipeManagementSystem.model.User;
import com.geekster.RecipeManagementSystem.repository.IRecipeRepo;
import com.geekster.RecipeManagementSystem.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    IRecipeRepo recipeRepo;

    @Autowired
    IUserRepo userRepo;


    public String postRecipe(Recipe recipe) {
        recipeRepo.save(recipe);
        return "Your recipe successfully posted!!";

    }

    public List<Recipe> getAllRecipe() {
        return recipeRepo.findAll();
    }

    public String editRecipe(String recipeName, Recipe recipe, String userEmail) {

        Recipe recipe1=recipeRepo.findByRecipeName(recipeName);
        if(recipe1 == null){
            return "Not found with this Recipe Name";
        }
        recipe1.setRecipeIngredients(recipe.getRecipeIngredients());
        recipe1.setRecipeInstructions(recipe.getRecipeInstructions());
        recipe1.setRecipeName(recipe.getRecipeName());

        User editerUser = userRepo.findFirstByUserEmail(userEmail);
        recipe1.setUser(editerUser);
        recipeRepo.save(recipe1);

        return "Recipe Sucessfully Updated";


    }

    public String deleteRecipe(String recipeName, String userToken, String userEmail) {
        //Check if any recipe is present with this name or not
        Recipe recipe=recipeRepo.findByRecipeName(recipeName);
        if(recipe == null){
            return "No recipe exist with the name ";
        }
        //Get Authenticate User
        User user=userRepo.findFirstByUserEmail(userEmail);
        //Check if the recipe owner user and authenticate user is same then only we can delete the recipe

        //Get owner User
        User owner=recipe.getUser();
        if(user.getUserId()!= owner.getUserId()){
            return "Only owner can delete the recipe!!";
        }
        recipeRepo.delete(recipe);
        return "Recipe successfully deleted!!";
    }

}
