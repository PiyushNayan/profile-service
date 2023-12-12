package com.example.profileservice.FeignClient;

import com.example.profileservice.dto.SearchDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.profileservice.FeignClient.SolrFallback;

@FeignClient(value = "SearchEntity",url = "http://10.20.3.177:8089/quora", fallbackFactory = SolrFallback.class)
public interface SolrFeign {

    @PostMapping("/search/addProfile")
    void saveProfile(@RequestBody SearchDto profileDto);

    @PutMapping("/search/update-profile")
    void updateUser(@RequestParam("profileId") String profileId, @RequestBody SearchDto searchDto);
}
