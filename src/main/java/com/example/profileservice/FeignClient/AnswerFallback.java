//package com.example.profileservice.FeignClient;
//
//import com.example.profileservice.ApiHandler.ApiResponse;
//import org.springframework.stereotype.Component;
//
//@Component
//public class AnswerFallback implements FallbackFactory<AnswerFeign> {
//
//    @Override
//    public AnswerFeign create(Throwable throwable) {
//        return new AnswerFeign() {
//
//            @Override
//            public ApiResponse<Answer> getAnswerById(String answerId) {
//                return null;
//            }
//        };
//    }
//}


