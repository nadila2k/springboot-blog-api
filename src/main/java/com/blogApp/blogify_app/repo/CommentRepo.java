package com.blogApp.blogify_app.repo;


import com.blogApp.blogify_app.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends JpaRepository<Comments,Long> {
}
