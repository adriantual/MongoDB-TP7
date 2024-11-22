package com.example.backend_BD2_TP_mongo.domain;


import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;
import java.util.List;

@Document(collection = "posts")
@Getter
@Setter
public class Post {


    private String id;
    private String title;
    private String text;
    private List<String> tags;
    private String resume;
    private List<String> relatedLinks;
    private String author;
    private Date date;

}