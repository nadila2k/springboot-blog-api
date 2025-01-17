package com.blogApp.blogify_app.service.serviceInterface;

import com.blogApp.blogify_app.dto.PostDto;
import com.blogApp.blogify_app.dto.ResponsePostDto;
import com.blogApp.blogify_app.model.Post;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostInterface {

    List<ResponsePostDto> getAllPost();

    ResponsePostDto addPost(PostDto postDto, MultipartFile image) throws IOException;


    ResponsePostDto updatePost(PostDto postDto, long id);

    ResponsePostDto updatePost(PostDto postDto, long id, MultipartFile file) throws IOException;

    void deletePost(long id);
}
