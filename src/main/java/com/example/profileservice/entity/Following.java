package com.example.profileservice.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@Document(collection = "following")
public class Following {

    private String profileId;

    @Id
    private String followingId;

//    private String followingName;
}
