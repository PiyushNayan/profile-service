package com.example.profileservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Followers {

    @Id
    private String followerId;

    private String profileId;
    private String followerName;

}
