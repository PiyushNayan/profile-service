package com.example.profileservice.service.impl;

import com.example.profileservice.FeignClient.SolrFeign;
import com.example.profileservice.dto.ProfileDto;
import com.example.profileservice.dto.SearchDto;
import com.example.profileservice.entity.Profile;
import com.example.profileservice.entity.ProfileFollowersFollowing;
import com.example.profileservice.entity.Ranking;
import com.example.profileservice.repository.ProfileRepository;
import com.example.profileservice.repository.RankingRepository;
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
        private SecProfileRepo secProfileRepo;

        @Autowired
        private RankingRepository rankingRepository;

        @Autowired
        private SolrFeign solrFeign;


        public Profile getProfileById(String profileId) {
            return profileRepository.getProfileByProfileId(profileId);
        }

        @Override
        public Boolean addProfile(ProfileDto profileDto ) {
            Profile profile = new Profile();
            BeanUtils.copyProperties(profileDto, profile);


            Profile newProfile = profileRepository.save(profile);

            ProfileFollowersFollowing profileFollowersFollowing = new ProfileFollowersFollowing();

            profileFollowersFollowing.setProfileId(profileDto.getProfileId());
            profileFollowersFollowing.setFollowers(new ArrayList<>());
            profileFollowersFollowing.setFollowing(new ArrayList<>());
            profileFollowersFollowing.setCategories(new ArrayList<>());

            secProfileRepo.save(profileFollowersFollowing);

            SearchDto searchDto = new SearchDto();
            searchDto.setProfileId(profileDto.getProfileId());
            searchDto.setAvatar(profileDto.getProfileAvatar());
            searchDto.setPoints(profile.getPoints());
            searchDto.setSearchTerm(profileDto.getProfileName());
            solrFeign.saveProfile(searchDto);

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
        public Boolean addFollower(String profileId, String followerId) {
            ProfileFollowersFollowing profile = secProfileRepo.findByProfileId(profileId);
            List<String> followersList = profile.getFollowers();
            followersList.add(followerId);
            profile.setFollowers(followersList);
            secProfileRepo.save(profile);
            return true;
        }

        @Override
        public Boolean followUser(String profileId, String followingId) {
            ProfileFollowersFollowing profile = secProfileRepo.findByProfileId(profileId);
            List<String> followingList = profile.getFollowing();
            followingList.add(followingId);
            profile.setFollowing(followingList);
            secProfileRepo.save(profile);
            return true;
        }

        @Override
        public Boolean removeFollower(String profileId, String followerId) {
            ProfileFollowersFollowing profile = secProfileRepo.findByProfileId(profileId);
            if (profile != null) {
              List<String> followers =   profile.getFollowers();
              followers.remove(followerId);
              profile.setFollowers(followers);
                secProfileRepo.save(profile);
                return true;
//                List<String> followersList = profile.getFollowers();
//                if (followersList.contains(followerId)) {
//                    followersList.remove(followerId);
//                    try {
//                        secProfileRepo.save(profile);
//                        return true;
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
            }
            return false;
        }

        @Override
        public Boolean unfollowUser(String profileId, String followingId) {
            ProfileFollowersFollowing profile = secProfileRepo.findByProfileId(profileId);
            if (profile != null) {
                List<String> followingList = profile.getFollowing();
                followingList.remove(followingId);
                profile.setFollowing(followingList);
                secProfileRepo.save(profile);
                //                if (followersList.contains(followingId)) {
//                    followersList.remove(followingId);
//                    try {
//                        secProfileRepo.save(profile);
//                        return true;
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
            }
            return false;
        }
}
