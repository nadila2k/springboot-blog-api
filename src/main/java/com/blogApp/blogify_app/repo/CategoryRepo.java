package com.blogApp.blogify_app.repo;

import com.blogApp.blogify_app.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo  extends JpaRepository<Category,Long> {
}
