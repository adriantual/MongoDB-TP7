package com.example.backend_BD2_TP_mongo.api;

import com.example.backend_BD2_TP_mongo.domain.Post;
import com.example.backend_BD2_TP_mongo.domain.dto.AuthorPostCountDTO;
import com.example.backend_BD2_TP_mongo.domain.dto.PostDTO;
import com.example.backend_BD2_TP_mongo.domain.dto.ShortPostDTO;

import java.util.List;

public interface PostService {
    List<ShortPostDTO> getLatest4Posts();

    List<PostDTO> getPostsByAuthor(String nombreautor);

    List<PostDTO> searchPosts(String text);

    List<AuthorPostCountDTO> getPostCounts();

    PostDTO findPostById(String id);
    void insertPost(PostDTO post);
}