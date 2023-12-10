package com.example.profileservice.repository;

import com.example.profileservice.entity.Followers;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowerRepository extends MongoRepository<Followers, String> {
}
