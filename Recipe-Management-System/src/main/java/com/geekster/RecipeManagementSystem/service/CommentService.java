package com.geekster.RecipeManagementSystem.service;

import com.geekster.RecipeManagementSystem.model.Comments;
import com.geekster.RecipeManagementSystem.model.User;
import com.geekster.RecipeManagementSystem.repository.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    ICommentRepo commentRepo;

    public String add(Comments comment) {
        commentRepo.save(comment);
        return "Comment has been added!!!";
    }

    public String saveCommentForRecipe(Comments comment, String recipeName, User user) {
        if(comment!=null) {
            commentRepo.save(comment);
            return "comment saved";
        }
        return "comment can not be null.";
    }

    public String addComment(Comments comment) {
        commentRepo.save(comment);
        return "commented";
    }


}
