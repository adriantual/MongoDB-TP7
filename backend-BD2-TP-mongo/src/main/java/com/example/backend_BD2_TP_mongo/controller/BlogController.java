package com.example.backend_BD2_TP_mongo.controller;

import com.example.backend_BD2_TP_mongo.api.PostService;
import com.example.backend_BD2_TP_mongo.domain.dto.AuthorPostCountDTO;
import com.example.backend_BD2_TP_mongo.domain.dto.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class BlogController {

    @Autowired
    private PostService postService;

    @GetMapping("byauthor")
    List<AuthorPostCountDTO> getAuthorsAndNumberOfPost() {
        return this.postService.getPostCounts();
    }

    @GetMapping("/search/{text}")
    List<PostDTO> getPostsByText(@PathVariable String text) {
        return this.postService.searchPosts(text);
    }
}