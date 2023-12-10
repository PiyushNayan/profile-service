package com.example.profileservice.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "business_profile")
public class BusinessProfile {
    @Id
    private String businessProfileId;

    private String businessName;
    private String businessProfileName;

    @OneToOne
    @JoinColumn(name = "profile_id", nullable = false, unique = true)
    private Profile profile;


//    @OneToOne(mappedBy = "businessProfile")
//    private Ranking ranking;
}
