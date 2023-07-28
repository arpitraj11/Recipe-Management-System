package com.geekster.RecipeManagementSystem.service;

import com.geekster.RecipeManagementSystem.model.AuthenticationToken;
import com.geekster.RecipeManagementSystem.repository.IAuthenticationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    IAuthenticationRepo authenticationRepo;
    public void saveAuthtoken(AuthenticationToken authToken) {
        authenticationRepo.save(authToken);
    }

    public boolean authenticate(String userEmail, String userToken) {

        AuthenticationToken authToken = authenticationRepo.findFirstByTokenValue(userToken);

        if(authToken == null) {
            return false;
        }
        String tokenConnectedEmail = authToken.getUser().getUserEmail();
        return tokenConnectedEmail.equals(userEmail);
    }
}
