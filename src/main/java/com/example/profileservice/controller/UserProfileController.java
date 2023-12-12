package com.example.profileservice.controller;

import com.example.profileservice.ApiHandler.ApiResponse;
import com.example.profileservice.dto.ProfileDto;
import com.example.profileservice.dto.ProfileResponseDto;
import com.example.profileservice.entity.Profile;
import com.example.profileservice.entity.ProfileFollowersFollowing;
import com.example.profileservice.repository.ProfileRepository;
import com.example.profileservice.repository.SecProfileRepo;
import com.example.profileservice.service.ProfileService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/quora/profile")
public class UserProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private SecProfileRepo secProfileRepo;

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
            apiResponse = new ApiResponse<>("404", e.getMessage());
        }
        return apiResponse;
    }

//
//    @GetMapping("/getProfile/{profileId}")
//    public ApiResponse<Profile> getProfile(@PathVariable("profileId") String profileId) {
//        ApiResponse<Profile> apiResponse;
//        ProfileResponseDto responseDto = new ProfileResponseDto();
//        try {
//            Profile profile = profileService.getProfileById(profileId);
//
//            if (profile==null) {
//                apiResponse = new ApiResponse<>("404", "Check the profile id and try again, profile not found");
//            } else {
//                BeanUtils.copyProperties(profile, responseDto);
//                apiResponse = new ApiResponse<>(profile);
//            }
//        } catch (Exception e) {
//            apiResponse = new ApiResponse<>("404", "Check the profile id and try again, profile not found");
//        }
//
//        return apiResponse;
//    }


    @GetMapping("/getProfileView/{profileId}")
    public ProfileResponseDto getProfileView(@PathVariable("profileId") String profileId) {
        ProfileResponseDto responseDto = new ProfileResponseDto();
        try {
            Profile profile = profileService.getProfileById(profileId);
            ProfileFollowersFollowing profileFollowersFollowing = secProfileRepo.findByProfileId(profileId);
//            if (profile!=null) {
                BeanUtils.copyProperties(profile, responseDto);
//            }
//            if(profileFollowersFollowing!=null){
                responseDto.setFollowers(profileFollowersFollowing.getFollowers());
                responseDto.setFollowing(profileFollowersFollowing.getFollowing());
//            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return responseDto;
    }

    @GetMapping("/getProfile/{profileId}")
    public Profile getProfile(@PathVariable("profileId") String profileId) {
        Profile profile;
        ProfileResponseDto responseDto = new ProfileResponseDto();
        try {
             profile = profileService.getProfileById(profileId);
            return profile;

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


//    @GetMapping("/profiles")
//    public List<Profile> getAllProfiles() {
//        ApiResponse<List<Profile>> apiResponse;
//        Iterable<Profile> profiles = profileService.getAllProfiles();
//        List<Profile> profilesList = new ArrayList<>();
//        for (Profile profile : profiles) {
//            profilesList.add(profile);
//        }
//        if (profilesList.isEmpty())
//            apiResponse = new ApiResponse<>("404", "No profiles found");
//        else
//            apiResponse = new ApiResponse<>(profilesList);
//
//        return profilesList;
//    }

    @GetMapping("/profiles")
    public List<ProfileResponseDto> getAllProfiles() {
        ApiResponse<List<Profile>> apiResponse;
        Iterable<Profile> profiles = profileService.getAllProfiles();
        List<Profile> profilesList = new ArrayList<>();
        for (Profile profile : profiles) {
            profilesList.add(profile);
        }

        List<ProfileResponseDto> responseDtos = new ArrayList<>();
        for (Profile profile:profilesList) {
            ProfileResponseDto profileResponseDto = new ProfileResponseDto();
            BeanUtils.copyProperties(profile, profileResponseDto);
            responseDtos.add(profileResponseDto);
        }
        return responseDtos;
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


    @PostMapping("/addFollower")
    public ApiResponse<Boolean> addFollower(@RequestParam("profileId") String profileId, @RequestParam("followerId") String followerId) {
        ApiResponse<Boolean> apiResponse;
        try {
            Boolean inserted = profileService.addFollower(profileId, followerId);
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

    @PostMapping("/followUser")
    public ApiResponse<Boolean> followUser(@RequestParam("profileId") String profileId, @RequestParam("followingId") String followingId) {
        ApiResponse<Boolean> apiResponse;
        try {
            Boolean inserted = profileService.followUser(profileId, followingId);
            if (inserted) {
                apiResponse = new ApiResponse<>(true);
            } else {
                apiResponse = new ApiResponse<>("404", "Could not follow");
            }
        } catch (Exception e) {
            apiResponse = new ApiResponse<>("404", "Could not follow");
        }
        return apiResponse;
    }

    @DeleteMapping("/removeFollower")
    public ApiResponse<String> removeFollower(@RequestParam("profileId") String profileId, @RequestParam("followerId") String followerId) {
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

    @PostMapping("/unfollowUser")
    public ApiResponse<String> unfollowUser(@RequestParam("profileId") String profileId, @RequestParam("followerId") String followingId) {
        ApiResponse<String> apiResponse;
        try {
            Boolean remove = profileService.unfollowUser(profileId, followingId);
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

    @PostMapping("/addCategories")
    public ApiResponse<Boolean> addCategories(@RequestParam("profileId") String profileId, @RequestBody List<String> categories) {
        ApiResponse<Boolean> apiResponse;
        try {
            ProfileFollowersFollowing followersFollowing = secProfileRepo.findByProfileId(profileId);
            followersFollowing.setCategories(categories);
            secProfileRepo.save(followersFollowing);

            apiResponse = new ApiResponse<>(true);

        } catch (Exception e) {
            apiResponse = new ApiResponse<>("404", "Could not add Follower");
        }
        return apiResponse;
    }

    @PutMapping("/updatePoints")
    public void updatePoints(@RequestParam("profileId") String profileId, @RequestParam("points") int points){
        Profile profile = profileRepository.findById(profileId).get();
        int points1 = profile.getPoints();
        profile.setPoints(points+points1);
        profileRepository.save(profile);
    }

}
