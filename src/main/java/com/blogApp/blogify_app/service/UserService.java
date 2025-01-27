package com.blogApp.blogify_app.service;

import com.blogApp.blogify_app.dto.UserDto;
import com.blogApp.blogify_app.model.AppUser;
import com.blogApp.blogify_app.repo.UserRepo;
import com.blogApp.blogify_app.service.serviceInterface.UserInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService implements UserInterface {

    private  final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;

    @Override
    public AppUser addUser(UserDto userDto) {
        if (userRepo.existsByEmail(userDto.getEmail())) {
           throw new IllegalArgumentException("Email is already in use");
        }
        AppUser user = new AppUser();
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepo.save(user);
    }


    @Override
    public AppUser updateUser(UserDto userDto , long id) {



        return  userRepo.findById(id)
                .map(existingUser ->{
                    existingUser.setId(userDto.getId());
                    existingUser.setUserName(userDto.getUserName());
                    existingUser.setEmail(userDto.getEmail());
                    existingUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
                    return userRepo.save(existingUser);
                })
                .orElseThrow(() -> new NoSuchElementException("User Not Found"));
    }



    @Override
    public void deleteUser(long id) {
        userRepo.findById(id).ifPresentOrElse(userRepo::delete,() ->{ throw new NoSuchElementException("User not fiund");});

    }

    @Override
    public List<AppUser> getAllUser() {
        return userRepo.findAll();
    }

    public AppUser convertToAppUser(UserDto userDto){

        return new AppUser(userDto.getId(), userDto.getUserName(), userDto.getEmail(), userDto.getPassword());
    }
}
