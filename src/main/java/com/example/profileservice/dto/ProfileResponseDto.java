package com.example.profileservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProfileResponseDto {

    private String profileName;
    private String profileType;
    private String profileAvatar;
    private String profileStatus;
    private String points;
    private String profileEmail;
    private List<String> followers;
    private List<String> following;
}
