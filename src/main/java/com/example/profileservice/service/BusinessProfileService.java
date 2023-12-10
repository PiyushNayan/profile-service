package com.example.profileservice.service;

import com.example.profileservice.dto.BusinessProfileDto;
import com.example.profileservice.entity.BusinessProfile;
import com.example.profileservice.entity.Profile;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public interface BusinessProfileService {
    BusinessProfile getProfileById(String profileId);
    Boolean addBusinessProfile(BusinessProfileDto businessProfileDto );
    Boolean updateProfileById(String businessProfileId, BusinessProfileDto businessProfileDto);
    Profile deleteProfileById(String profileId);
    int getPointsByBusinessProfileId(String businessProfileId);
}
