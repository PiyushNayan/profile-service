package com.example.profileservice.repository;

import com.example.profileservice.entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, String> {
//    int getPointsByProfileId(String profileId);
}
