package com.example.profileservice.service.impl;

import com.example.profileservice.dto.FollowerDto;
import com.example.profileservice.dto.FollowingDto;
import com.example.profileservice.dto.ProfileDto;
import com.example.profileservice.entity.Followers;
import com.example.profileservice.entity.Following;
import com.example.profileservice.entity.Profile;
import com.example.profileservice.entity.ProfileFollowersFollowing;
import com.example.profileservice.repository.FollowerRepository;
import com.example.profileservice.repository.FollowingRepository;
import com.example.profileservice.repository.ProfileRepository;
import com.example.profileservice.repository.SecProfileRepo;
import com.example.profileservice.service.ProfileService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProfileServiceImpl implements ProfileService {

        @Autowired
        private ProfileRepository profileRepository;

        @Autowired
        private FollowingRepository followingRepository;

        @Autowired
        private FollowerRepository followerRepository;

        @Autowired
        private SecProfileRepo secProfileRepo;

        public Profile getProfileById(String profileId) {
            return profileRepository.getProfileByProfileId(profileId);
        }

        @Override
        public Boolean addProfile(ProfileDto profileDto ) {
            Profile profile = new Profile();
            BeanUtils.copyProperties(profileDto, profile);
            String profileId = UUID.randomUUID().toString();
            profile.setProfileId(profileId);

            Profile newProfile = profileRepository.save(profile);
            ProfileFollowersFollowing profileFollowersFollowing = new ProfileFollowersFollowing();
            profileFollowersFollowing.setProfileId(profileId);
            profileFollowersFollowing.setFollowers(profileDto.getFollowers());
            profileFollowersFollowing.setFollowing(profileDto.getFollowing());
            secProfileRepo.save(profileFollowersFollowing);
            return Objects.nonNull(newProfile);
        }

        @Override
        public List<Profile> getAllProfiles () {
            return profileRepository.findAll();
        }

        @Override
        public Boolean updateProfileById(String profileId, ProfileDto profileDto) {
            Optional<Profile> optionalProfile = profileRepository.findById(profileId);
            if(optionalProfile.isPresent()){
                Profile existingProfile = optionalProfile.get();
                BeanUtils.copyProperties(profileDto, existingProfile);
                profileRepository.save(existingProfile);
                return true;
            }
            else {
                return false;
            }
        }

        @Override
        public Profile deleteProfileById(String profileId) {
            profileRepository.deleteById(profileId);
            return null;
        }

        @Override
        public int getPointsByProfileId(String profileId) {
            return profileRepository.getPointsByProfileId(profileId);
        }

    @Override
    public Boolean addFollower(FollowerDto followerDto) {
        return null;
    }

    @Override
    public Boolean followUser(FollowingDto followingDto) {
        return null;
    }

    @Override
    public Boolean removeFollower(String profileId, String followerId) {
        return null;
    }

    @Override
    public Boolean unfollow(String profileId, String followingId) {
        return null;
    }

//        @Override
//        public Boolean followUser(FollowingDto followingDto) {
//            Following following = new Following();
//            following.setProfileId(followingDto.getProfileId());
//            following.setFollowingId(UUID.randomUUID().toString());
//            followingRepository.save(following);
//            Profile profile = profileMongoRepository.findByProfileId(followingDto.getProfileId());
//            List<Following> follow = profile.getFollowingList();
//            follow.add(following);
//            profileMongoRepository.save(profile);
//            return Objects.nonNull(following);
//        }

//        @Override
//        public Boolean addFollower(String profileId, FollowerDto followerDto) {
//            Profile profile = profileMongoRepository.findByProfileId(profileId);
//            List<String> followersList = profile.getFollowers();
//            followersList.add(followerDto.getFollowerName());
//            profileMongoRepository.save(profile);
//            return Objects.nonNull(followerDto.getFollowerName());
//        }
//
//        @Override
//        public Boolean followUser(FollowingDto followingDto) {
//            Profile profile = profileMongoRepository.findByProfileId(followingDto.getProfileId());
//            List<String> followingList = profile.getFollowing();
//            followingList.add(followingDto.getFollowingName());
//            profileMongoRepository.save(profile);
//            return Objects.nonNull(followingDto.getFollowingName());
//        }

//        @Override
//        public Boolean addFollower(String profileId, String followername) {
//            Profile profile = profileMongoRepository.findByProfileId(profileId);
//            List<String> followersList = profile.getFollowers();
//            followersList.add(followername);
//            profileMongoRepository.save(profile);
//            return Objects.nonNull(followername);
//        }
//
//        @Override
//        public Boolean followUser(FollowingDto followingDto) {
//            Profile profile = profileMongoRepository.findByProfileId(followingDto.getProfileId());
//            List<String> followingList = profile.getFollowing();
//            followingList.add(followingDto.getFollowingName());
//            profileMongoRepository.save(profile);
//            return Objects.nonNull(followingDto.getFollowingName());
//        }
//
//        @Override
//        public Boolean removeFollower(String profileId, String followerId) {
//            ProfileFollowersFollowing profile = profileMongoRepository.findByProfileId(profileId);
//            List<Followers> followersList = profile.getFollowers();
//            int count = 0, index = 0;
//            for (Followers follower : followersList) {
//                String id = follower.getFollowerId();
//                count++;
//                if(id==followerId)
//                    index = count;
//            }
//            followersList.remove(index-1);
//            profileMongoRepository.save(profile);
//            followerRepository.deleteById(followerId);
//            return true;
//        }

//        @Override
//        public Boolean unfollow(String profileId, String followingId) {
//            ProfileFollowersFollowing profile = profileMongoRepository.findByProfileId(profileId);
//            List<Following> followingList = profile.getFollowing();
//            int count = 0, index = 0;
//            for (Following following : followingList) {
//                String id = following.getFollowingId();
//                count++;
//                if(id==followingId)
//                    index = count;
//            }
//            followingList.remove(index-1);
//            profileMongoRepository.save(profile);
//            followingRepository.deleteById(followingId);
//            return true;
//        }
    //
//        @Override
//        public Boolean unfollowUser (String profileId, String followingName) {
//            Profile profile = profileMongoRepository.findByProfileId(profileId);
//            List<String> following = profile.getFollowing();
//            if(following.contains(followingName)) {
//                following.remove(followingName);
//                return true;
//            }
//            else
//                return false;
//        }


//        @Override
//        public Boolean addFollower(FollowerDto followerDto) {
//            Followers follower = new Followers();
//            BeanUtils.copyProperties(followerDto, follower);
//            follower.setFollowerId(UUID.randomUUID().toString());
//            followerRepository.save(follower);
//            ProfileFollowersFollowing profile = new ProfileFollowersFollowing();
//            profile.setProfileId(followerDto.getProfileId());
//            List<Followers> followersList = profile.getFollowers();
//            followersList.add(follower);
//            profileMongoRepository.save(profile);
//            return true;
//        }

//        @Override
//        public Boolean followUser(FollowingDto followingDto) {
//            Following following = new Following();
//            BeanUtils.copyProperties(followingDto, following);
//            following.setFollowingId(UUID.randomUUID().toString());
//            followingRepository.save(following);
//            ProfileFollowersFollowing profile = profileMongoRepository.findByProfileId(followingDto.getProfileId());
//            List<Following> followingList = profile.getFollowing();
//            followingList.add(following);
//            profileMongoRepository.save(profile);
//            return true;
//        }

        @Override
        public Boolean addFollower(String profileId, String followerId) {
            Followers follower = followerRepository.findById(followerId).get();
            ProfileFollowersFollowing profileFollowersFollowing = secProfileRepo.findByProfileId(profileId);
            List<String> followersList = profileFollowersFollowing.getFollowers();
            followersList.add(follower.getFollowerName());
        }

}
