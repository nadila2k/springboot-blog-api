package com.blogApp.blogify_app.controller;

import com.blogApp.blogify_app.dto.PostDto;
import com.blogApp.blogify_app.dto.ResponsePostDto;
import com.blogApp.blogify_app.model.Post;
import com.blogApp.blogify_app.response.ApiResponse;
import com.blogApp.blogify_app.service.serviceInterface.PostInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/post")
public class PostsController {

    private final PostInterface postInterface;

    @PostMapping
    public ResponseEntity<ApiResponse> addPost(@RequestPart PostDto postDto , @RequestPart MultipartFile image){
        try {
            ResponsePostDto post = postInterface.addPost(postDto,image);
            return ResponseEntity.ok().body(new ApiResponse("Post add successfully",post));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Post Create Fail",e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updatePost(@RequestBody PostDto postDto, @PathVariable long id){
        try {
            ResponsePostDto post = postInterface.updatePost(postDto,id);
            return ResponseEntity.ok().body(new ApiResponse("Post Update Successfully",post));
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("",e.getMessage()));
        }
    }

    @PutMapping("/postImage/{id}")
    public ResponseEntity<ApiResponse> updatePostWithImage(@RequestPart PostDto postDto, @PathVariable long id, @RequestPart MultipartFile file){
        try {
            ResponsePostDto post = postInterface.updatePost(postDto,id,file);
            return ResponseEntity.ok().body(new ApiResponse("Post Update Successfully",post));
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("",e.getMessage()));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("",e.getMessage()));
        }
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable long id){
        try {
            postInterface.deletePost(id);
            return ResponseEntity.ok().body(new ApiResponse("Post Delete",null));
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body( new ApiResponse("",e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getPost(){
        List<ResponsePostDto> posts = postInterface.getAllPost();
        return ResponseEntity.ok().body(new ApiResponse("",posts));
    }



}
