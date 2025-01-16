package com.blogApp.blogify_app.service.serviceInterface;

import com.blogApp.blogify_app.model.Category;

import java.util.List;

public interface CategoryInterface {

    Category addcategory(Category category);
    Category updateCategory(Category  category,long id);
    void DeleteCategory(long id);
    List<Category> getAllCategory();

}
