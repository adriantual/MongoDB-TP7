package com.example.backend_BD2_TP_mongo.controller;

import com.example.backend_BD2_TP_mongo.api.PostService;
import com.example.backend_BD2_TP_mongo.domain.Post;
import com.example.backend_BD2_TP_mongo.domain.dto.AuthorPostCountDTO;
import com.example.backend_BD2_TP_mongo.domain.dto.PostDTO;
import com.example.backend_BD2_TP_mongo.domain.dto.ShortPostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/latest")
    public List<ShortPostDTO> getLatestPosts() {
        return postService.getLatest4Posts();
    }

    @GetMapping("/author/{nombreautor}")
    public List<PostDTO> getPostsByAuthor(@PathVariable String nombreautor) {
        List<PostDTO> lista= this.postService.getPostsByAuthor(nombreautor);
        return lista;
    }

    @GetMapping("/{id}")
    public List<PostDTO> getPostById(@PathVariable String id) {return Collections.singletonList(this.postService.findPostById(id));}

    @PostMapping
    public void createPost(@RequestBody PostDTO post) {
        postService.insertPost(post);
    }
}