package com.blogApp.blogify_app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;
    private String public_Id;
    private String url;

    @ManyToOne
    @JoinColumn(
            name = "category_Id",
            referencedColumnName = "id"
    )
    private Category category;



    @OneToMany(mappedBy = "post")
    private List<Comments> comments;

    @ManyToOne
    @JoinColumn(
            name = "appUser_Id",
    referencedColumnName = "id")
    private AppUser appUser;

    public Post(long id, String title, String content, Category category, String url, String public_Id, AppUser appUser) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.url = url;
        this.public_Id = public_Id;
        this.appUser = appUser;
    }
}
