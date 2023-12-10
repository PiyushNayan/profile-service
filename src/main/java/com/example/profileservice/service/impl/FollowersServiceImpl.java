//package com.example.profileservice.service.impl;
//
//import com.example.profileservice.dto.FollowerDto;
//import com.example.profileservice.entity.Followers;
//import com.example.profileservice.entity.Profile;
//import com.example.profileservice.repository.FollowersRepository;
//import com.example.profileservice.repository.ProfileRepositoryForFollowers;
//import com.example.profileservice.service.FollowersService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Objects;
//import java.util.UUID;
//
//@Service
//public class FollowersServiceImpl implements FollowersService {
//
//    @Autowired
//    private FollowersRepository followerRepository;
//
//    @Autowired
//    private ProfileRepositoryForFollowers profileMongoRepository;
//
////    public List<Followers> getFollowersByProfileId(String profileId) {
////        return followerRepository.findByProfileId(profileId);
////    }
////
////    public List<Followers> getFollowingByFollowerId(String followerId) {
////        return followerRepository.findByFollowerId(followerId);
////    }
//
////    public Boolean addFollower (FollowerDto followerDto) {
////        Followers follower = new Followers();
////        follower.setProfileId(followerDto.getProfileId());
////        follower.setFollowerId(UUID.randomUUID().toString());
////        followerRepository.save(follower);
////        Profile profile = profileMongoRepository.findByProfileId(followerDto.getProfileId());
////        List<Followers> followers = profile.getFollowersList();
////        followers.add(follower);
////        profileMongoRepository.save(profile);
////        return Objects.nonNull(follower);
////    }
//
//}
