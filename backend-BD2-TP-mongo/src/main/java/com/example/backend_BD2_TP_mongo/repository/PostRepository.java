package com.example.backend_BD2_TP_mongo.repository;

import com.example.backend_BD2_TP_mongo.domain.Post;
import com.example.backend_BD2_TP_mongo.domain.dto.PostDTO;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Repository
public class PostRepository {

    private final MongoCollection<Document> postCollection;

    @Autowired
    public PostRepository(MongoClient mongoClient) {

        MongoDatabase database = mongoClient.getDatabase("blog");
        this.postCollection = database.getCollection("posts");
        this.postCollection.createIndex(Indexes.text("text"));
    }


    public List<Document> findPostByText(String text) {
        List<Document> result = new ArrayList<>();
        postCollection.find(Filters.text(text)).into(result);
        return result;
    }

    public List<Document> findAuthorsAndNumberOfPost() {
        List<Document> docAuthorsAndNumberOfPost = new ArrayList<>();

        postCollection.aggregate(List.of(Aggregates.group("$author", Accumulators.sum("count", 1))
        )).into(docAuthorsAndNumberOfPost);

        return docAuthorsAndNumberOfPost;
    }


    public Document findPostById(String id) {
        AtomicReference<Document> postDoc = new AtomicReference<>();

        postCollection.find(Filters.eq("_id", new ObjectId(id)))
                .forEach(postDoc::set);

        return postDoc.get();
    }

    public List<Document> findLatestPosts() {
        List<Document> result = new ArrayList<>();


        Bson projectionFields = Projections.fields(
                Projections.include("_id", "title", "resume"));
        postCollection.find()
                .projection(projectionFields)
                .sort(Sorts.descending("date"))
                .limit(4)
                .into(result);

        return result;
    }


    public List<Document> findByAuthor(String author) {
       List<Document> docs = postCollection.find(Filters.eq("author", author)).into(new ArrayList<>());
        return docs;
    }



public void save(PostDTO post) {
    Date date= new Date();
    Document doc = new Document("title", post.getTitle())
            .append("text", post.getText())
            .append("tags", post.getTags())
            .append("resume", post.getResume())
            .append("relatedLinks", post.getRelatedlinks())
            .append("author", post.getAuthor())
            .append("date", date);
    postCollection.insertOne(doc);
}


}