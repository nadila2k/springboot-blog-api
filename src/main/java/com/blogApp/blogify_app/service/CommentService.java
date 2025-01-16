package com.blogApp.blogify_app.service;

import com.blogApp.blogify_app.model.Comments;
import com.blogApp.blogify_app.repo.CommentRepo;
import com.blogApp.blogify_app.service.serviceInterface.CommentInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CommentService implements CommentInterface {

    private final CommentRepo commentRepo;


    @Override
    public List<Comments> getAllComment() {
        return commentRepo.findAll();
    }

    @Override
    public Comments addComment(Comments comment) {
        return commentRepo.save(comment);
    }

    @Override
    public Comments updateComment(Comments comment, long id) {
        return commentRepo.findById(id).map(
                comments -> {
                    comments.setId(comment.getId());
                    comments.setContent(comment.getContent());
                    comments.setAppUser(comment.getAppUser());
                    comments.setPost(comment.getPost());

                    return commentRepo.save(comments);
                }
        ).orElseThrow(() -> { throw new NoSuchElementException("comment not found");
        });
    }

    @Override
    public void deleteComment(long id) {

        commentRepo.findById(id).ifPresentOrElse(commentRepo::delete,() -> {throw new NoSuchElementException("Comment Not Found");});
    }
}
