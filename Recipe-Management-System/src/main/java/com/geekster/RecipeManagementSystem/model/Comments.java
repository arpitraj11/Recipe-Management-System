package com.geekster.RecipeManagementSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentId;

    private String userComments;

    @ManyToOne
    @JoinColumn(name = "fk_commenter_user_id")
    private User commenterUser;

    @ManyToOne
    @JoinColumn(name = "fk_recipe_id")
    private Recipe recipeComment;

}
