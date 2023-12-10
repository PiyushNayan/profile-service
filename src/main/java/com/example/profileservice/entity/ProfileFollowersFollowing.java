package com.example.profileservice.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Data
@Document(collection = "profile_followers")
public class ProfileFollowersFollowing {

    @Id
    private String profileId;
    private List<String> followers;
    private List<String> following;
}
