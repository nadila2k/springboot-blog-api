package com.blogApp.blogify_app.controller;

import com.blogApp.blogify_app.dto.UserDto;
import com.blogApp.blogify_app.model.AppUser;
import com.blogApp.blogify_app.response.ApiResponse;
import com.blogApp.blogify_app.service.UserService;
import com.blogApp.blogify_app.service.serviceInterface.UserInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/user")
public class UserController {

    private final UserInterface userInterface;

    @PostMapping
    public ResponseEntity<ApiResponse> createUser(@RequestBody UserDto userDto) {
        try {
            AppUser user = userInterface.addUser(userDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("User created successfully", user));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Error", e.getMessage()));
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateUser(@RequestBody UserDto userDto , @PathVariable long id){

        try {
            AppUser appUser = userInterface.updateUser(userDto,id);
            return ResponseEntity.ok().body(new ApiResponse("User update successfully",appUser));
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("user Update Fail",e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllUser(){
        try {
            List<AppUser> users = userInterface.getAllUser();
            return ResponseEntity.ok().body(new ApiResponse("",users));
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("users not found",e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable long id){
        try {
            userInterface.deleteUser(id);
            return ResponseEntity.ok().body(new ApiResponse("user Delete successfully",null));
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("user not found",e.getMessage()));
        }
    }
}
