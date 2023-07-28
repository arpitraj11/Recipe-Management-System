package com.geekster.RecipeManagementSystem.service;

import com.geekster.RecipeManagementSystem.model.AuthenticationToken;
import com.geekster.RecipeManagementSystem.model.Comments;
import com.geekster.RecipeManagementSystem.model.DTO.SignInInput;
import com.geekster.RecipeManagementSystem.model.DTO.SignUpOutput;
import com.geekster.RecipeManagementSystem.model.Recipe;
import com.geekster.RecipeManagementSystem.model.User;
import com.geekster.RecipeManagementSystem.repository.IRecipeRepo;
import com.geekster.RecipeManagementSystem.repository.IUserRepo;
import com.geekster.RecipeManagementSystem.service.HashingUtility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private IUserRepo userRepo;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    RecipeService recipeService;

    @Autowired
    CommentService commentService;

    @Autowired
    IRecipeRepo recipeRepo;


    public SignUpOutput signUpUser(User user) {
        boolean signUpStatus = true;
        String signUpStatusMessage = null;

        String newEmail = user.getUserEmail();

        if(newEmail == null)
        {
            signUpStatusMessage = "Invalid email";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        //check if this user email already exists ??
        User existingUser = userRepo.findFirstByUserEmail(newEmail);

        if(existingUser != null) {
            signUpStatusMessage = "Email already registered!!!";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus, signUpStatusMessage);
        }
        //hash the password: encrypt the password
        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(user.getUserPassword());

            //saveAppointment the user with the new encrypted password

            user.setUserPassword(encryptedPassword);
            userRepo.save(user);

            return new SignUpOutput(signUpStatus, "User registered successfully!!!");
        }
        catch(Exception e)
        {
            signUpStatusMessage = "Internal error occurred during sign up";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }
    }

    public String signInUser(SignInInput signInInput) {

        String signInStatusMessage = null;
        String signInEmail = signInInput.getEmail();

        if(signInEmail == null) {
            signInStatusMessage = "Invalid email";
            return signInStatusMessage;
        }

        //check if this user email already exists ??
        User existingUser = userRepo.findFirstByUserEmail(signInEmail);

        if(existingUser == null) {
            signInStatusMessage = "Email not registered!!!";
            return signInStatusMessage;
        }
        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(signInInput.getPassword());
            if(existingUser.getUserPassword().equals(encryptedPassword))
            {
                //session should be created since password matched and user id is valid
                AuthenticationToken authToken  = new AuthenticationToken(existingUser);
                authenticationService.saveAuthtoken(authToken);

                return "Session Created with the Token "+ authToken.getTokenValue();
            }
            else {
                signInStatusMessage = "Invalid credentials!!!";
                return signInStatusMessage;
            }
        }
        catch(Exception e) {
            signInStatusMessage = "Internal error occurred during sign in";
            return signInStatusMessage;
        }
    }

    public String postRecipe(Recipe recipe, String userEmail , String userToken) {
        User recipeOwner = userRepo.findFirstByUserEmail(userEmail);
        recipe.setUser(recipeOwner);
        return recipeService.postRecipe(recipe);

    }

    public List<Recipe> getAllRecipe(String userEmail, String userToken) {
        return recipeService.getAllRecipe();
    }

    public String addComment(Comments comment, String commenterEmail) {

        if(comment != null ){
            User commenter = userRepo.findFirstByUserEmail(commenterEmail);
            comment.setCommenterUser(commenter);
            return commentService.add(comment);
        }else{
            return "Cannot comment on Invalid recipe";
        }

    }

    public String editRecipe(String recipeName, Recipe recipe , String userEmail) {
        return recipeService.editRecipe(recipeName, recipe , userEmail);
    }

    public String deleteMyRecipe(String recipeName, String userEmail, String userToken) {
        return recipeService.deleteRecipe(recipeName, userToken,userEmail);
    }

    public String commentOnRecipe(Comments comment, String recipeName, String email, String token) {

        User user=userRepo.findFirstByUserEmail(email);
        return commentService.saveCommentForRecipe(comment,recipeName,user);
    }


}
