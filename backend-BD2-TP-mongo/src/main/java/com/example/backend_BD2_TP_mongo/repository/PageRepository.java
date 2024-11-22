package com.example.backend_BD2_TP_mongo.repository;



import com.example.backend_BD2_TP_mongo.domain.Page;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Repository
public class PageRepository {

    private final MongoCollection<Document> pageCollection;


    @Autowired
    public PageRepository(MongoClient mongoClient) {
        MongoDatabase database = mongoClient.getDatabase("blog");
        this.pageCollection = database.getCollection("pages");
    }

    public Document findById(String id) {
        AtomicReference<Document> pageDoc = new AtomicReference<>();
        long total=pageCollection.countDocuments();

        Document document = pageCollection.find(Filters.eq("_id", new ObjectId(id))).first();
        pageDoc.set(document);

        return pageDoc.get();
 }

    public void save(Page page) {

        Document doc = new Document("title", page.getTitle())
                .append("text", page.getText())
                .append("author", page.getAuthor())
                .append("date", new Date());

        AtomicReference<Document> insertedDoc = new AtomicReference<>();
        pageCollection.insertOne(doc);
        insertedDoc.set(doc);
    }


}