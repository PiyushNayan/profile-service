package com.example.profileservice.service;

import com.example.profileservice.dto.ProfileDto;
import com.example.profileservice.entity.Profile;

public interface ProfileService {
    Profile getProfileById(String profileId);
    Boolean addProfile(ProfileDto profileDto );
    Iterable<Profile> getAllProfiles();
    Boolean updateProfileById(String profileId, ProfileDto profileDto);
    Profile deleteProfileById(String profileId);
    int getPointsByProfileId(String profileId);

    Boolean addFollower(String profileId, String followerId);
    Boolean followUser(String profileId, String followingId);
    Boolean removeFollower(String profileId, String followerId);
    Boolean unfollowUser(String profileId, String followingId);
}
