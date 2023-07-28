package com.geekster.RecipeManagementSystem.controller;

import com.geekster.RecipeManagementSystem.model.Recipe;
import com.geekster.RecipeManagementSystem.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class RecipeController {

    @Autowired
    RecipeService recipeService;



}
