package com.example.profileservice.controller;

import com.example.profileservice.ApiHandler.ApiResponse;
import com.example.profileservice.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/quora/profile")
public class RankingController {

//    @Autowired
//    private RankingService rankingService;
//
//    @GetMapping("/getRanking/{profileId}")
//    public ApiResponse<Integer> getRanking(@PathVariable("profileId") String profileId) {
//        return new ApiResponse<>(rankingService.getRating(profileId));
//    }
}
