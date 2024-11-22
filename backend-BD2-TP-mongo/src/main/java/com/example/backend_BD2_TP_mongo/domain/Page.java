package com.example.backend_BD2_TP_mongo.domain;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "pages")
@Getter
@Setter
public class Page {


    private String id;
    private String title;
    private String text;
    private String author;
    private Date date;

}