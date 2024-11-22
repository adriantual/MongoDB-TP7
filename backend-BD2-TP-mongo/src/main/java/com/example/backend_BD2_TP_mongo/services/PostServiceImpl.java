package com.example.backend_BD2_TP_mongo.services;

import com.example.backend_BD2_TP_mongo.api.PostService;
import com.example.backend_BD2_TP_mongo.domain.Post;
import com.example.backend_BD2_TP_mongo.domain.dto.AuthorPostCountDTO;
import com.example.backend_BD2_TP_mongo.domain.dto.PostDTO;
import com.example.backend_BD2_TP_mongo.domain.dto.ShortPostDTO;
import com.example.backend_BD2_TP_mongo.repository.PostRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

   @Override
    public PostDTO findPostById(String id) {
        Document postDoc = postRepository.findPostById(id);

       return PostDTO.fromDocument(postDoc);
   }


    @Override
    public List<ShortPostDTO> getLatest4Posts() {
        return postRepository.findLatestPosts().stream()
                .map(ShortPostDTO::fromDocument)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> getPostsByAuthor(String nombreautor) {
        List<Document> postList = this.postRepository.findByAuthor(nombreautor);

        return postList.stream()
                .map(PostDTO::fromDocument)
                .collect(Collectors.toList());

   }


    @Override
    public List<PostDTO> searchPosts(String text) {
        List<Document> postList = this.postRepository.findPostByText(text);

        return postList.stream()
                .map(PostDTO::fromDocument)
                .collect(Collectors.toList());
   }

    @Override
    public List<AuthorPostCountDTO> getPostCounts() {
        return postRepository.findAuthorsAndNumberOfPost().stream()
                .map(AuthorPostCountDTO::fromDocument)
                .collect(Collectors.toList());


    }



    @Override
    public void insertPost(PostDTO post) {
        postRepository.save(post);


    }
}

