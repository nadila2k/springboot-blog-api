package com.blogApp.blogify_app.controller;

import com.blogApp.blogify_app.dto.CategoryDto;
import com.blogApp.blogify_app.model.Category;
import com.blogApp.blogify_app.response.ApiResponse;
import com.blogApp.blogify_app.service.serviceInterface.CategoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/category")
public class CategoryController {

    private final CategoryInterface categoryInterface;

    @PostMapping
    public ResponseEntity<ApiResponse> addCategory(@RequestBody CategoryDto categoryDto){
        try {
            Category category= categoryInterface.addcategory(categoryDto);
            return ResponseEntity.ok().body(new ApiResponse("Category create successfully",category));
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Category create fail",e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable long id){
        try {
            Category category = categoryInterface.updateCategory(categoryDto,id);
            return ResponseEntity.ok().body(new ApiResponse("category update successfully",category));
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("category not found",e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable long id){
        try {
            categoryInterface.DeleteCategory(id);
            return ResponseEntity.ok().body(new ApiResponse("category delete successfully",null));
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("category not found",e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllCategory(){

        return ResponseEntity.ok().body(new ApiResponse("",categoryInterface.getAllCategory()));
    }
}
