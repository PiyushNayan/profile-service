package com.example.profileservice.FeignClient;

import com.example.profileservice.dto.SearchDto;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class SolrFallback implements FallbackFactory<SolrFeign> {
    @Override
    public SolrFeign create(Throwable throwable) {
        return new SolrFeign() {

            @Override
            public void saveProfile(@RequestBody SearchDto profileDto)
            {
            }

            @Override
            public void updateUser(@RequestParam("profileId") String profileId, @RequestBody SearchDto searchDto)
            {}
        };
    }
}
