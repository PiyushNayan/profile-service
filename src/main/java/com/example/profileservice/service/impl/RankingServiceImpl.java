//package com.example.profileservice.service.impl;
//
//import com.example.profileservice.FeignClient.AnswerFeign;
//import com.example.profileservice.repository.RankingRepository;
//import com.example.profileservice.service.RankingService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class RankingServiceImpl implements RankingService {
//
//    @Autowired
//    RankingRepository rankingRepository;
//
//
//    public int updatePoints(List<answerIds>, List<questionIds>) {
//        int points;
//        for(String id: answerIds) {
//            Answer answer = AnswerFeign.getAnswerById(answerId);
//            int upvotes = answer.getUpVotes();
//            int downvotes = answer.getDownVotes();
//            points += upvotes;
//            points -= downvotes;
//        }
//
//        points += questionIds.size()*5;
//        return points;
//    }
//}
