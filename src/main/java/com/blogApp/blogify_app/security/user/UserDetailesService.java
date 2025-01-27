package com.blogApp.blogify_app.security.user;

import com.blogApp.blogify_app.model.AppUser;
import com.blogApp.blogify_app.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailesService implements UserDetailsService {

    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = (AppUser) Optional.ofNullable(userRepo.findByEmail(email)).orElseThrow(() -> new UsernameNotFoundException("user Not Found"));
        return new UserDetailes(user);
    }
}
