package com.example.profileservice.repository;

import com.example.profileservice.entity.Profile;
import com.example.profileservice.entity.ProfileFollowersFollowing;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecProfileRepo extends MongoRepository<ProfileFollowersFollowing, String> {

    ProfileFollowersFollowing findByProfileId(String profileId);
}
