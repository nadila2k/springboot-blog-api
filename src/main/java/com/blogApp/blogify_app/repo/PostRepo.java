package com.blogApp.blogify_app.repo;

import com.blogApp.blogify_app.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post,Long> {
}
