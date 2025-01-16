package com.blogApp.blogify_app.service.serviceInterface;

import com.blogApp.blogify_app.model.Comments;

import javax.xml.stream.events.Comment;
import java.util.List;

public interface CommentInterface {

    List<Comments> getAllComment();
    Comments addComment(Comments comment);
    Comments updateComment(Comments comment , long id);
    void deleteComment(long id);
}
