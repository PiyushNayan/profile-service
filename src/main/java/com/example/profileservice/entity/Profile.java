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
    private String profileAvatar;
    private String profileStatus;
    private String rankingId;
    private String points;
    private String profileEmail;

    @OneToOne
    @JoinColumn(name = "business_profile_id", nullable = true, unique = true)
    private BusinessProfile businessProfile;

    private String role;

//    @OneToOne(mappedBy = "profile")
//    private Ranking ranking;


}
