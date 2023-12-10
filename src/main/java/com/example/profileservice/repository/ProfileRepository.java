package com.example.profileservice.repository;

import com.example.profileservice.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, String> {
    Profile getProfileByProfileId(String profileId);
    int getPointsByProfileId(String profileId);
}
