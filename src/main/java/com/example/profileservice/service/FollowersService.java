package com.example.profileservice.service;


import com.example.profileservice.dto.FollowerDto;
import com.example.profileservice.entity.Followers;

import java.util.List;

public interface FollowersService {

//    List<Followers> getFollowersByProfileId(String profileId);
//    List<Followers> getFollowingByFollowerId(String followerId);
    Boolean addFollower (FollowerDto followerDto);
}
