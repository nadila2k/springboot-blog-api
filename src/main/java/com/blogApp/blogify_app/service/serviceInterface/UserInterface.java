package com.blogApp.blogify_app.service.serviceInterface;

import com.blogApp.blogify_app.dto.UserDto;
import com.blogApp.blogify_app.model.AppUser;

import java.util.List;

public interface UserInterface {

    AppUser addUser(UserDto userDto);
    AppUser updateUser(UserDto userDto,long id);



    void  deleteUser(long id);
    List<AppUser> getAllUser();
}
