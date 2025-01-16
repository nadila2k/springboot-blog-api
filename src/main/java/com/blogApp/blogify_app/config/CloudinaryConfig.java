package com.blogApp.blogify_app.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary(){

        Map<String,String> config = new HashMap<>();
        config.put("cloud_name", "dbiddrued");
        config.put("api_key", "367988313781747");
        config.put("api_secret", "c30rn4ORcvPGW_U5BsnTKChzeMc");

        return new Cloudinary(config);
    }
}
