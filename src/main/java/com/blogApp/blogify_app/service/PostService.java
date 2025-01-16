package com.blogApp.blogify_app.service;

import com.blogApp.blogify_app.config.CloudinaryConfig;
import com.blogApp.blogify_app.dto.PostDto;
import com.blogApp.blogify_app.model.AppUser;
import com.blogApp.blogify_app.model.Category;
import com.blogApp.blogify_app.model.Post;
import com.blogApp.blogify_app.repo.CategoryRepo;
import com.blogApp.blogify_app.repo.PostRepo;
import com.blogApp.blogify_app.repo.UserRepo;
import com.blogApp.blogify_app.service.serviceInterface.PostInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PostService implements PostInterface {

    private final PostRepo postRepo;
    private final CategoryRepo categoryRepo;
    private final UserRepo userRepo;
    private final CloudinaryConfig cloudinary;

    @Override
    public List<Post> getAllPost() {
        return postRepo.findAll();
    }



    @Override
    public Post addPost(PostDto postDto, MultipartFile image) throws IOException {

        Category category = categoryRepo.findById(postDto.getCategory().getId()).orElseThrow(()-> new NoSuchElementException("Not Found Category"));
        AppUser user =  userRepo.findById(postDto.getAppUser().getId())
                .orElseThrow(() -> new NoSuchElementException("User Not Found"));

        Map imageDetailes = cloudinary.cloudinary().uploader().upload(image.getBytes(),Map.of());
        postDto.setPublic_Id(imageDetailes.get("public_id").toString());
        postDto.setUrl(imageDetailes.get("url").toString());
        return postRepo.save(post(postDto,category,user));
    }

    @Override
    public Post updatePost(PostDto postDto, long id) {


        Category category = categoryRepo.findById(postDto.getCategory().getId()).orElseThrow(()-> new NoSuchElementException("Not Found Category"));
        AppUser user =  userRepo.findById(postDto.getAppUser().getId())
                .orElseThrow(() -> new NoSuchElementException("User Not Found"));


        return postRepo.findById(id).map(
                post -> {
                    return getPost(postDto, category, user, post);
                }
        ).orElseThrow(() -> new NoSuchElementException("Post Not Found "));
    }

    @Override
    public Post updatePost(PostDto postDto, long id, MultipartFile file) throws IOException {


        Category category = categoryRepo.findById(postDto.getCategory().getId()).orElseThrow(()-> new NoSuchElementException("Not Found Category"));
        AppUser user =  userRepo.findById(postDto.getAppUser().getId())
                .orElseThrow(() -> new NoSuchElementException("User Not Found"));


        return postRepo.findById(id).map(
                post -> {


                    try {
                        cloudinary.cloudinary().uploader().destroy(postDto.getPublic_Id(),Map.of());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    Map imageDetails = null;
                    try {
                        imageDetails = cloudinary.cloudinary().uploader().upload(file.getBytes(), Map.of());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    postDto.setPublic_Id(imageDetails.get("public_id").toString());
                    postDto.setUrl(imageDetails.get("url").toString());

                    return getPost(postDto, category, user, post);
                }
        ).orElseThrow(() -> new NoSuchElementException("Post Not Found "));

    }

    private Post getPost(PostDto postDto, Category category, AppUser user, Post post) {
        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setCategory(category);
        post.setUrl(postDto.getUrl());
        post.setPublic_Id(postDto.getPublic_Id());
        post.setAppUser(user);
        return postRepo.save(post);
    }

    @Override
    public void deletePost(long id) {

    }

    public Post post(PostDto postDto , Category category, AppUser appUser){
        return new Post(postDto.getId(),postDto.getTitle(),postDto.getContent(),category,postDto.getUrl(),postDto.getPublic_Id(),appUser);
    }


}
