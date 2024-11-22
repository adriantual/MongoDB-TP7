package com.example.backend_BD2_TP_mongo.domain.dto;

import lombok.Builder;
import lombok.Getter;
import org.bson.Document;

@Builder
@Getter
public class ShortPostDTO {

    private Document _id;
    private String title;
    private String resume;

    public static ShortPostDTO fromDocument(Document docPost){

        Document id = new Document("$oid", docPost.getObjectId("_id").toHexString());

        return ShortPostDTO.builder()
                ._id(id)
                .title(docPost.getString("title"))
                .resume(docPost.getString("resume"))
                .build();
    }
}