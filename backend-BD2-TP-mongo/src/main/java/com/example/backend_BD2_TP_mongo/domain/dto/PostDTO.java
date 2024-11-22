package com.example.backend_BD2_TP_mongo.domain.dto;

import com.example.backend_BD2_TP_mongo.domain.Post;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bson.Document;

import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter
public class PostDTO {
    private Document _id;
    @NotBlank
    private String title;
    @NotBlank
    private String text;
    private List<String> tags;
    @NotBlank
    private String resume;
    private List<String> relatedlinks;
    @NotBlank
    private String author;
    private Document date;

    public static PostDTO fromDocument(Document docPost){

        Document id = new Document("$oid", docPost.getObjectId("_id").toHexString());
        Document date = new Document("$date", docPost.getDate("date"));

        return PostDTO.builder()
                ._id(id)
                .title(docPost.getString("title"))
                .text(docPost.getString("text"))
                .tags(docPost.getList("tags", String.class))
                .resume(docPost.getString("resume"))
                .relatedlinks(docPost.getList("relatedLinks", String.class))
                .author(docPost.getString("author"))
                .date(date)
                .build();
    }



}


