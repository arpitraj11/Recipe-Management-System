package com.geekster.RecipeManagementSystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recipeId;

    private String recipeName;

    private String recipeIngredients;

    private String recipeInstructions;

    @ManyToOne
    @JoinColumn(name = "fk_recipe_posted_user_id")
    private User user;





}
