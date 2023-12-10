package com.example.profileservice.repository;

import com.example.profileservice.entity.Following;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowingRepository extends MongoRepository<Following, String> {
}
