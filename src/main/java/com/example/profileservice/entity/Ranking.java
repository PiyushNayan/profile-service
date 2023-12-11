package com.example.profileservice.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user_ranking")
public class Ranking {

    @Id
    private String id;
    private int points;
    private String profile;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(nullable = true, name = "profile_fk")
//    private Profile profile;

//    @OneToOne
//    @JoinColumn(name = "business_profile_id", nullable = true, unique = true)
//    private BusinessProfile businessProfile;

}
