package com.blogApp.blogify_app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String public_Id;
    private String url;

    @OneToOne
    @JoinColumn(
            name = "post_Id",
            referencedColumnName = "id"
    )
    private Post post;
}
