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

    @OneToOne
    @JoinColumn(name = "profile_id", nullable = false, unique = true)
    private Profile profile;

    @OneToOne
    @JoinColumn(name = "business_profile_id", nullable = true, unique = true)
    private BusinessProfile businessProfile;
}
