package com.example.profileservice.controller;

import com.example.profileservice.ApiHandler.ApiResponse;
import com.example.profileservice.dto.FollowerDto;
import com.example.profileservice.dto.FollowingDto;
import com.example.profileservice.dto.ProfileDto;
import com.example.profileservice.entity.Profile;
import com.example.profileservice.service.FollowersService;
import com.example.profileservice.service.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin
@RequestMapping("/profile")
public class UserProfileController {

    @Autowired
    private ProfileService profileService;

//    @Autowired
//    private FollowersService followersService;

    @PostMapping("/addProfile")
    public ApiResponse<Boolean> addProfile (@RequestBody ProfileDto profileDto) {
        ApiResponse<Boolean> apiResponse;
        try {
            Boolean inserted = profileService.addProfile(profileDto);
            if (inserted) {
                apiResponse = new ApiResponse<>(true);
            } else {
                apiResponse = new ApiResponse<>("404", "Could not add profile");
            }
        } catch (Exception e) {
            apiResponse = new ApiResponse<>("404", "Could not add profile");
        }
        return apiResponse;
    }


    @GetMapping("/getProfile/{profileId}")
    public ApiResponse<Profile> getProfile(@PathVariable("profileId") String profileId) {
        ApiResponse<Profile> apiResponse;
        try {
            Profile profile = profileService.getProfileById(profileId);

            if (profile==null) {
                apiResponse = new ApiResponse<>("404", "Check the profile id and try again, profile not found");
            } else {
                apiResponse = new ApiResponse<>(profile);
            }
        } catch (Exception e) {
            apiResponse = new ApiResponse<>("404", "Check the profile id and try again, profile not found");
        }

        return apiResponse;
    }


    @GetMapping("/profiles")
    public List<Profile> getAllProfiles() {
        ApiResponse<List<Profile>> apiResponse;
        Iterable<Profile> profiles = profileService.getAllProfiles();
        List<Profile> profilesList = new ArrayList<>();
        for (Profile profile : profiles) {
            profilesList.add(profile);
        }
        if (profilesList.isEmpty())
            apiResponse = new ApiResponse<>("404", "No profiles found");
        else
            apiResponse = new ApiResponse<>(profilesList);

        return profilesList;
    }

    @PutMapping("/{profileId}")
    public ApiResponse<Boolean> updateProfile(@PathVariable("profileId") String profileId, @RequestBody ProfileDto profileDto) {
        ApiResponse<Boolean> apiResponse;
        Profile profile = profileService.getProfileById(profileId);
        if (profile!=null) {
            profileService.updateProfileById(profileId, profileDto);
            apiResponse = new ApiResponse<>(true);
        } else {
            apiResponse = new ApiResponse<>("404", "Check the profile id and try again, profile not found");
        }
        return apiResponse;

    }


    @DeleteMapping("/{profileId}")
    public ApiResponse<Boolean> deleteProfile(@PathVariable("profileId") String profileId) {
        ApiResponse<Boolean> apiResponse;
        Profile profile = profileService.getProfileById(profileId);
        if (profile!=null) {
            profileService.deleteProfileById(profileId);
            apiResponse = new ApiResponse<>(true);
        } else
            apiResponse = new ApiResponse<>("404", "Check the profile id and try again, profile not found");

        return apiResponse;
    }

    @GetMapping("/getRating/{profileId}")
    public ApiResponse<Integer> getPointsByProfileId(@PathVariable("profileId") String profileId) {
        ApiResponse<Integer> apiResponse;
        int points = profileService.getPointsByProfileId(profileId);
            apiResponse = new ApiResponse<>(points);

        return apiResponse;
    }

//    @PostMapping("/addFollower")
//    public ApiResponse<Boolean> addFollower (@RequestBody FollowerDto followerDto) {
//        ApiResponse<Boolean> apiResponse;
//        try {
//            Boolean inserted = followersService.addFollower(followerDto);
//            if (inserted) {
//                apiResponse = new ApiResponse<>(true);
//            } else {
//                apiResponse = new ApiResponse<>("404", "Could not add Follower");
//            }
//        } catch (Exception e) {
//            apiResponse = new ApiResponse<>("404", "Could not add Follower");
//        }
//        return apiResponse;
//    }

    @PostMapping("/follow")
    public ApiResponse<Boolean> followUser (@RequestBody FollowingDto followingDto) {
        ApiResponse<Boolean> apiResponse;
        try {
            Boolean inserted = profileService.followUser(followingDto);
            if (inserted) {
                apiResponse = new ApiResponse<>(true);
            } else {
                apiResponse = new ApiResponse<>("404", "Try again");
            }
        } catch (Exception e) {
            apiResponse = new ApiResponse<>("404", "Try again");
        }
        return apiResponse;
    }

    @PostMapping("/addFollower/")
    public ApiResponse<Boolean> addFollower (@RequestBody FollowerDto followerDto) {
        ApiResponse<Boolean> apiResponse;
        try {
            Boolean inserted = profileService.addFollower(followerDto);
            if (inserted) {
                apiResponse = new ApiResponse<>(true);
            } else {
                apiResponse = new ApiResponse<>("404", "Could not add Follower");
            }
        } catch (Exception e) {
            apiResponse = new ApiResponse<>("404", "Could not add Follower");
        }
        return apiResponse;
    }

    @PutMapping("/removeFollower/{profileId}/{followerId}")
    public ApiResponse<String> removeFollower(@PathVariable("profileId") String profileId, @PathVariable("followerId") String followerId) {
        ApiResponse<String> apiResponse;
        try {
            Boolean remove = profileService.removeFollower(profileId, followerId);
            if(remove) {
                apiResponse = new ApiResponse<>("Follower removed");
            }

            else {
                apiResponse = new ApiResponse<>("404", "Could not remove follower");
            }
        }

        catch (Exception e) {
            apiResponse = new ApiResponse<>("404", "Could not remove follower");
        }

        return apiResponse;
    }

    @PutMapping("/unfollowUser/{profileId}/{followingId}")
    public ApiResponse<String> unfollow(@PathVariable("profileId") String profileId, @PathVariable("followerId") String followingId) {
        ApiResponse<String> apiResponse;
        try {
            Boolean remove = profileService.removeFollower(profileId, followingId);
            if(remove) {
                apiResponse = new ApiResponse<>("Follower removed");
            }

            else {
                apiResponse = new ApiResponse<>("404", "Could not unfollow");
            }
        }

        catch (Exception e) {
            apiResponse = new ApiResponse<>("404", "Could not unfollow");
        }

        return apiResponse;
    }

}
