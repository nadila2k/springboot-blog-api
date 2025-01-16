package com.blogApp.blogify_app.service;

import com.blogApp.blogify_app.model.Category;
import com.blogApp.blogify_app.repo.CategoryRepo;
import com.blogApp.blogify_app.service.serviceInterface.CategoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CategoryService implements CategoryInterface {

    private final CategoryRepo categoryRepo;
    @Override
    public Category addcategory(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public Category updateCategory(Category category , long id) {
        return categoryRepo.findById(id)
                .map(existing -> {
                    existing.setName(category.getName());
                    return categoryRepo.save(existing);
                }).orElseThrow(() ->new NoSuchElementException("Category Not Found"));
    }

    @Override
    public void DeleteCategory(long id) {

        categoryRepo.findById(id).ifPresentOrElse(
                categoryRepo::delete,
                ()-> {throw new NoSuchElementException("category not found");}
        );
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepo.findAll();
    }
}
