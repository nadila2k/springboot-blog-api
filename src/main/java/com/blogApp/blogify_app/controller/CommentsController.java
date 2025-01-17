package com.blogApp.blogify_app.controller;


import com.blogApp.blogify_app.model.Comments;
import com.blogApp.blogify_app.service.serviceInterface.CommentInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/post")
public class CommentsController {

    private final CommentInterface commentInterface;
    @GetMapping
    public ResponseEntity<List<Comments>> getAllComments() {
        List<Comments> comments = commentInterface.getAllComment();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    // Add a new comment
    @PostMapping
    public ResponseEntity<Comments> addComment(@RequestBody Comments comment) {
        Comments createdComment = commentInterface.addComment(comment);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    // Update an existing comment by ID
    @PutMapping("/{id}")
    public ResponseEntity<Comments> updateComment(@RequestBody Comments comment, @PathVariable long id) {
        Comments updatedComment = commentInterface.updateComment(comment, id);
        if (updatedComment != null) {
            return new ResponseEntity<>(updatedComment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a comment by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable long id) {

        commentInterface.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
