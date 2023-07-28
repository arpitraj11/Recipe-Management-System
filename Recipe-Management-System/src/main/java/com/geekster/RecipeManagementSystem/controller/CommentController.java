package com.geekster.RecipeManagementSystem.controller;

import com.geekster.RecipeManagementSystem.model.Comments;
import com.geekster.RecipeManagementSystem.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;
import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("addComment")
    String addComment(@RequestBody Comments comment)
    {
        return commentService.addComment(comment);
    }

}
