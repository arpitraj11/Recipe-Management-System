package com.geekster.RecipeManagementSystem.repository;

import com.geekster.RecipeManagementSystem.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.stream.events.Comment;
import java.util.List;

public interface ICommentRepo extends JpaRepository<Comments, Long> {

}
