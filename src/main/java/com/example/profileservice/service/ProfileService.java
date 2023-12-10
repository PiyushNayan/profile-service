package com.example.profileservice.service;

import com.example.profileservice.dto.FollowerDto;
import com.example.profileservice.dto.FollowingDto;
import com.example.profileservice.dto.ProfileDto;
import com.example.profileservice.entity.Followers;
import com.example.profileservice.entity.Profile;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public interface ProfileService {
    Profile getProfileById(String profileId);
    Boolean addProfile(ProfileDto profileDto );
    Iterable<Profile> getAllProfiles();
    Boolean updateProfileById(String profileId, ProfileDto profileDto);
    Profile deleteProfileById(String profileId);
    int getPointsByProfileId(String profileId);

//    Boolean addFollower(String profileId, FollowerDto followerDto);
//    Boolean followUser(FollowingDto followingDto);

    Boolean addFollower(FollowerDto followerDto);
    Boolean followUser(FollowingDto followingDto);
    Boolean removeFollower(String profileId, String followerId);
    Boolean unfollow(String profileId, String followingId);
}
