package com.geekster.RecipeManagementSystem.controller;

import com.geekster.RecipeManagementSystem.model.Comments;
import com.geekster.RecipeManagementSystem.model.DTO.SignInInput;
import com.geekster.RecipeManagementSystem.model.DTO.SignUpOutput;
import com.geekster.RecipeManagementSystem.model.Recipe;
import com.geekster.RecipeManagementSystem.model.User;
import com.geekster.RecipeManagementSystem.service.AuthenticationService;
import com.geekster.RecipeManagementSystem.service.CommentService;
import com.geekster.RecipeManagementSystem.service.RecipeService;
import com.geekster.RecipeManagementSystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    @Autowired
    RecipeService recipeService;

    @Autowired
    AuthenticationService authenticationService;

    //sign up, sign in , sign out a particular user
    @PostMapping("user/signup")
    public SignUpOutput signUpUser(@RequestBody @Valid User user) {

        return userService.signUpUser(user);
    }

    @PostMapping("user/signIn")
    public String signInUser(@RequestBody @Valid SignInInput signInInput) {
        return userService.signInUser(signInInput);
    }
    @PostMapping("recipe")
    public String postRecipe(@RequestBody Recipe recipe , @RequestParam String userEmail, @RequestParam String userToken) {
        if (authenticationService.authenticate(userEmail, userToken)) {
            return userService.postRecipe(recipe, userEmail, userToken);
        } else {
            return "SignIn to Order";
        }
    }
    @GetMapping("recipe")
    public List<Recipe> getAllRecipe(@RequestParam String userEmail, @RequestParam String userToken) {
        if (authenticationService.authenticate(userEmail, userToken)) {

        }
        return userService.getAllRecipe(userEmail,userToken);
    }

    @PostMapping("comment")
    public String addComment(@RequestBody Comments comment, @RequestParam String commenterEmail, @RequestParam String commenterToken)
    {
        if(authenticationService.authenticate(commenterEmail,commenterToken)) {
            return userService.addComment(comment,commenterEmail);
        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }
    @PutMapping("recipe/{recipeName}")
    public String editRecipe(@PathVariable String recipeName,@RequestParam String userToken, @RequestParam String userEmail ,@RequestBody Recipe recipe){
        if(authenticationService.authenticate(userEmail, userToken)) {
            return userService.editRecipe(recipeName,recipe , userEmail);
        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }

    @DeleteMapping("recipe/{recipeName}")
    public String deleteRecipe(@PathVariable String recipeName,@RequestParam String userEmail,@RequestParam String userToken){
        if(authenticationService.authenticate(userEmail, userToken)) {
            return userService.deleteMyRecipe(recipeName,userEmail, userToken);
        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }
    @PostMapping("/recipe/comment/{RecipeName}")
    public String addComment(@Valid @RequestBody Comments comment, @PathVariable String recipeName, @RequestParam String email, @RequestParam String token){
        if(authenticationService.authenticate(email, token)) {
            return userService.commentOnRecipe(comment,recipeName,email,token);
        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }
}
