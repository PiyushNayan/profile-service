package com.example.profileservice.dto;

import com.example.profileservice.entity.Followers;
import com.example.profileservice.entity.Following;
import lombok.Data;

import java.util.List;

@Data
public class ProfileDto {

    private String profileId;
    private String profileName;
    private String profileDesc;
    private String profileType;
    private String profileAvatar;
    private String profileStatus;
    private String role;
    private String profileEmail;


}
