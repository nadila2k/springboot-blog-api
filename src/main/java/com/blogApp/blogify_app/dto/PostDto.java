package com.blogApp.blogify_app.dto;

import com.blogApp.blogify_app.model.AppUser;
import com.blogApp.blogify_app.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private long id;
    private String title;
    private String content;
    private String public_Id;
    private String url;
    private Category category;
    private AppUser appUser;

}
