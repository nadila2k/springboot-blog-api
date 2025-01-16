package com.blogApp.blogify_app.service;

import com.blogApp.blogify_app.dto.CategoryDto;
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
    public Category addcategory(CategoryDto categoryDto) {
        return categoryRepo.save(categoryReq(categoryDto));
    }

    @Override
    public Category updateCategory(CategoryDto categoryDto , long id) {
        return categoryRepo.findById(id)
                .map(existing -> {
                    Category category = existing;
                    return categoryRepo.save(categoryPut(categoryDto,category));
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

    public Category categoryReq(CategoryDto categoryDto){

        Category category = new Category(categoryDto.getId(), categoryDto.getName());
        return category;
    }

    public Category categoryPut(CategoryDto categoryDto , Category category){

        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        return category;
    }

}
