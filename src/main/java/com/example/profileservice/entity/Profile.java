package com.example.profileservice.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "user_profile")
public class Profile {

    @Id
    private String profileId;
    private String profileName;
    private String profileType;
    private String profileDesc;
    private String profileAvatar;
    private String profileStatus;
    private String role;
    private int points;
    private String profileEmail;

//
//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "ranking_fk",nullable = false)
//    private Ranking ranking;

    //    private String businessProfileId;

//    @OneToOne
//    @JoinColumn(name = "business_profile_id", nullable = true, unique = true)
//    private BusinessProfile businessProfile;



//    @OneToOne(mappedBy = "profile")
//    private Ranking ranking;


}
