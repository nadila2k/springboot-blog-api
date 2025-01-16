package com.blogApp.blogify_app.service.serviceInterface;

import com.blogApp.blogify_app.dto.CategoryDto;
import com.blogApp.blogify_app.model.Category;

import java.util.List;

public interface CategoryInterface {

    Category addcategory(CategoryDto categoryDto);
    Category updateCategory(CategoryDto categoryDto,long id);
    void DeleteCategory(long id);
    List<Category> getAllCategory();

}
