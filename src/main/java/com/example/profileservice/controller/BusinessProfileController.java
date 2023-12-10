package com.example.profileservice.controller;

import com.example.profileservice.ApiHandler.ApiResponse;
import com.example.profileservice.dto.BusinessProfileDto;
import com.example.profileservice.dto.ProfileDto;
import com.example.profileservice.entity.BusinessProfile;
import com.example.profileservice.service.BusinessProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/businessProfile")
public class BusinessProfileController {

    @Autowired
    private BusinessProfileService businessProfileService;

    @GetMapping("/getRating/{businessProfileId}")
    public ApiResponse<Integer> getPointsByProfileId(@PathVariable("businessProfileId") String businessProfileId) {
        ApiResponse<Integer> apiResponse;
        BusinessProfile businessProfile = businessProfileService.getProfileById(businessProfileId);
        if (businessProfile!=null) {
            int points = businessProfileService.getPointsByBusinessProfileId(businessProfileId);
            apiResponse = new ApiResponse<>(points);
        } else
            apiResponse = new ApiResponse<>("404", "Check the profile id and try again, profile not found");

        return apiResponse;
    }

    @PostMapping("/addBusinessProfile")
    public ApiResponse<Boolean> addBusinessProfile (@RequestBody BusinessProfileDto businessProfileDto) {
        ApiResponse<Boolean> apiResponse;
        try {
            Boolean inserted = businessProfileService.addBusinessProfile(businessProfileDto);
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
}
