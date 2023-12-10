//package com.example.profileservice.FeignClient;
//
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//
//@FeignClient(value = "Answer",url = "http://localhost:8088",fallbackFactory = AnswerFallback.class)
//public interface AnswerFeign {
//
//    @RequestMapping(method = RequestMethod.GET,value="")
//    ApiResponse<Answer> getAnswerById(@PathVariable("answerId") String answerId);
//}